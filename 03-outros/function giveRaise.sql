delimiter :

create function giveRaise (oldval double, amount double) 
returns double
deterministic
begin
	
	declare newval double;
	set newval= oldval * (1 + amount);
	return newval;
end :
