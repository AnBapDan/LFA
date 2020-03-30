grammar Calculator;

program:
    stat* EOF
    ;

stat:
      expr? F? NEWLINE                    #Expression
  |   print? F? NEWLINE                   #Println
  |   assignment? F? NEWLINE              #Variable
  ;

print: 'print' expr;
assignment: expr '->' ID;


expr:
      op=('+'|'-') expr               #ExprIntegerSignal
    | n=expr '/' d=expr               #ExprFracc
    | expr op=('*'|':') expr          #ExprMultDivMod
    | expr op=('+'|'-') expr          #ExprAddSub
    | Integer                         #ExprInteger
    | '(' expr ')'                    #ExprParent
    | ID                              #ExprId
    ;

Integer: [0-9]+;
F: ';';
NEWLINE : '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '//' .*? '\n' -> skip;
ID: [a-zA-Z_]+;
