grammar Hello;

main: r+;
r: greetings | bye;
greetings: 'Hello' ID;
bye: 'Bye' ID;

ID: [a-zA-Z]+;
WS: [ \n\t\r]+ -> skip;
