#!/usr/bin/perl

#===================================
#
# This script generates sourcecode for
# graphs out of csv automatically. The 
# Sourcecode is metapost code.
#
# Author: Sascha Martensen 
#
#===================================

use strict;
use warnings;

# defining variables
	#input
my $infile = $ARGV[0];
my $outfile = $ARGV[1];
	#reading
my @text;
my $i;
	#generating
my $j;
my $row;
my @row;
my $index=0;
my $max_x=1;
my $min_x=-1;
my $max_y=1;
my $min_y=-1;
my @draw;



# reading csv-file

open(FILEIN,"<$infile");
while(<FILEIN>){
	$text[$i++]=$_;
}
close(FILEIN);

# generate the graph-values

foreach $row (@text){
	@row=split(",",$row);
	pop(@row);
	if($row[0] > $max_x){ 
		$max_x = $row[0]+1;
	}
	if($row[0] < $min_x){ 
		$min_x = $row[0]-1;
	}
	for($j=1; $j<=$#row; $j++){
		if($row[$j] > $max_y){
			$max_y = $row[$j]+1;
		} 
		if($row[$j] < $min_y){
			$min_y = $row[$j]-1;
		}
		$row[$j]="A[".$index."]:=(".$row[0]."cm,".$row[$j]."cm);\n";
		push(@draw, "A[".$index++."]");	
	}
	shift(@row);
	$row = join("",@row);
}

my $header= 
	"verbatimtex
\\documentclass[7pt]{article}
\\usepackage[T1]{fontenc}
\\usepackage{charter}
\\begin{document}
etex
beginfig (1)
	pair X[];
	X[0]:= (0,0);
	X[1]:= ($max_x cm,0);
	X[2]:= ($min_x cm,0);
	pair Y[];
	Y[0]:= X[0];
	Y[1]:= (0,$max_y cm);
	Y[2]:= (0,$min_y cm);
	
	draw Y[1]--Y[2]--Y[0]--X[2]--X[1];
	for i=$min_x cm step 0.5cm until $max_x cm:
		draw (i,-0.25cm)--(i,0.25cm);
	endfor;
	for i=$min_y cm step 0.5cm until $max_y cm:
		draw (-0.25cm,i)--(0.25cm,i);
	endfor;
	pair A[];\n";

my $draw ="draw ".join("--",@draw).";\n";

my $footer="endfig;
end;\n";
		
unshift(@text,$header);
push(@text,$draw);
push(@text,$footer);



open(FILEOUT,">$outfile");
print(FILEOUT join("",@text));

