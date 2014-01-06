@genom = split("",$genom);
@pattern = split("",$pattern);

for($i = 0; $i <= ($#genom-$#pattern+1); $i++)
{
	for($j = 0; $j <= $#pattern; $j++)
	{
		if($genom[$i+$j] ne $pattern[$j])
		{
			$missmatch++;
		}	
	}
	push(@result,$missmatch);
}