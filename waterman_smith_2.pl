#!/usr/bin/perl
#use strict;
use warnings;
#The implementation of Needleman-Wunsch
my($sequenz1,$sequenz2) = @ARGV;
my @Sequenz1 = split('',$sequenz1);
my @Sequenz2 = split('',$sequenz2);
if ($#Sequenz1 > $#Sequenz2){
	my @cache = @Sequenz1;
	@Sequenz1 = @Sequenz2;
	@Sequenz2 = @cache;
}
my @Matrix = ();
my $i;
my $j;
#=====================
# Create the matrix
#=====================

# initial row and collumn
for($i = 0; $i <= $#Sequenz1 + 1; $i++){
	$Matrix[$i][0][0] = 0;
}
for($j = 0; $j <= $#Sequenz2 + 1; $j++){
	$Matrix[0][$j][0] = 0;
}

# rest
for($i = 1; $i <= $#Sequenz1 + 1; $i++){
	for($j = 1; $j <= $#Sequenz2 +1; $j++){

		#checking equality
		if($Sequenz1[$i-1] eq $Sequenz2[$j-1]){
			$Matrix[$i][$j][0] = 2;
		}
		else{
			$Matrix[$i][$j][0] = -1;
		}

		#checkin for penaltys
		if($Matrix[$i-1][$j-1][0] >= $Matrix[$i][$j-1][0]){
			if($Matrix[$i-1][$j-1][0] >= $Matrix[$i-1][$j][0]){
				$Matrix[$i][$j][0] = $Matrix[$i][$j][0] + $Matrix[$i-1][$j-1][0];
				$Matrix[$i][$j][1] = "d";
			}
			else{
				$Matrix[$i][$j][0] = $Matrix[$i][$j][0] + $Matrix[$i-1][$j][0] - 1;
				$Matrix[$i][$j][1] = "u";
			}
		}
		else{
			if($Matrix[$i][$j-1][0] >= $Matrix[$i-1][$j][0]){
				$Matrix[$i][$j][0] = $Matrix[$i][$j][0] + $Matrix[$i][$j-1][0] - 1;
				$Matrix[$i][$j][1] = "l";
			}
			else{
				$Matrix[$i][$j][0] = $Matrix[$i][$j][0] + $Matrix[$i-1][$j][0] - 1;
				$Matrix[$i][$j][1] = "u";
			}
		if($Matrix[$i][$j][0] < 0){
			$Matrix[$i][$j][0] = 0;
		}
		}
	}
}
#====================
# Traceback
#====================

my @out1;
my @out2;
my @outcompare;
$i--;
$j--;

print("Score: ".$Matrix[$i][$j][0]."\n");

while($i > 0 || $j > 0){
#			print($i."\t".$j."\n");

			if($Matrix[$i][$j][1] eq "d" && $Matrix[$i][$j][0] > $Matrix[$i-1][$j-1][0]){
				unshift(@outcompare,"|");
				unshift(@out1,$Sequenz1[$i-1]);
				unshift(@out2,$Sequenz2[$j-1]);
				$i--;$j--;
			}
			elsif($Matrix[$i][$j][1] eq "d" ){
				unshift(@out1,$Sequenz1[$i-1]);
				unshift(@outcompare,"*");
				unshift(@out2,$Sequenz2[$j-1]);
				$i--;$j--;
			}
			elsif($Matrix[$i][$j][1] eq "l"){
				unshift(@out1,"-");
				unshift(@out2,$Sequenz2[$j-1]);
				unshift(@outcompare," ");
				$j--;
			}
			else{
				unshift(@out2,"_");
				unshift(@outcompare," ");
				unshift(@out1,$Sequenz1[$i-1]);
				$i--;
			}
}

# Printing the result

# entire matrix 

for($i = 0; $i <= $#Sequenz1 + 1; $i++){
	for($j = 0; $j <= $#Sequenz2 + 1; $j++){
		print($Matrix[$i][$j][0]."\t");
	}
	print("\n");
}
print("\n");
for($i = 1; $i <= $#Sequenz1 + 1;$i++){
	for($j = 1; $j <= $#Sequenz2 + 1;$j++){
		print($Matrix[$i][$j][1]."\t");
	}
	print("\n");
}
print(join(" ",@out1)."\n");
print(join(" ",@outcompare)."\n");
print(join(" ",@out2)."\n");
