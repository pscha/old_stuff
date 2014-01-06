#!/usr/bin/perl
use strict;
use warnings;

# menu 
	print(iterativ($ARGV[0])."\n");
	print(rekursiv($ARGV[0],0,1)."\n");
	print(dynamisch($ARGV[0])."\n");
# subs

sub iterativ{
	my $max = $_[0];
	my $x = 0;
	my $y = 1;

	for( $max; $max > 0 ;$max --){
		 ($y,$x) = ($x+$y,$y); 	#Gleichzeitige wertzuweisung für $x und $y mithilfe eines Arrays
	}
	return($y);
}

sub rekursiv{
	my $max = $_[0];
	my $x = $_[1];
	my $y = $_[2];

	if($max == 0){
		return(0);	#Wenn das maximum erreicht ist, dann brich ab.
	}
	return(rekursiv($max-1,$x,$y)+rekursiv($max-2,$x,$y));
}

sub dynamisch{
	my $max = $_[0];
	my @fib=(0,1);
	while($#fib <= $max){
		@fib=(@fib,$fib[-1]+$fib[-2]);
	}
	return($fib[-1]);
}
