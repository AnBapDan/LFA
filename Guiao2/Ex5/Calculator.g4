grammar Calculator;

program:
    stat* EOF
    ;

stat:
      expr? NEWLINE                   #Expression
  |   assignment? NEWLINE              #Variable
  ;

assignment: ID '=' expr;

expr:
      op=('+'|'-') expr               #ExprIntegerSignal
    | expr op=('*'|'/'|'%') expr      #ExprMultDivMod
    | expr op=('+'|'-') expr          #ExprAddSub
    | Integer                         #ExprInteger
    | '(' expr ')'                    #ExprParent
    | ID                              #ExprId
    ;

Integer: [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
COMMENT: '#' .*? '\n' -> skip;
ID: [a-zA-Z_]+;
