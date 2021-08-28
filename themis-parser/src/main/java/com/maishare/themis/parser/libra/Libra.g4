grammar Libra;
file: pair* ;
pair : ID ':' value ;
value: ('{' stat* '}') | STRING;
stat: superCompDecl | compDecl | varDecl | conditionDecl;
varDecl: ID EQUAL expr LINE_FEED;
superCompDecl: '@' compDecl;
compDecl:  LIBRA COLON compType COLON compName  params?  LINE_FEED;
compType: ID ;
compName: ID ;
params: '(' (expr) (',' (expr))* ')';
conditionDecl: 'if' conditionExpr COLON realBlock* ('else' COLON fakeBlock*)? 'end';
realBlock: (varDecl | compDecl | superCompDecl) ;
fakeBlock: (varDecl | compDecl | superCompDecl) ;
conditionExpr: contrastCondExpr followedCondition* ;
followedCondition: conditionOps expr ;
contrastCondExpr: expr conditionOps expr ;
conditionOps: '&&'
            | '||'
            | '=='
            | '!='
            ;
expr: expr '*' expr
    | expr '/' expr
    | expr ('+' | '-') expr
    | digits
    | externalVarName
    | STRING
    | ID
    | TRUE
    | FALSE
    | NIL
    ;
externalVarName: DOLLAR ID;
digits: DIGIT*;

WS : [ \t\r\n]+ -> skip ;
EQUAL: '=' ;
DOLLAR: '$' ;
LIBRA: 'libra' ;
LINE_FEED: ';' ;
TRUE: 'true' ;
FALSE: 'false' ;
ID : LETTER (LETTER|DIGIT)* ;
LETTER : [a-zA-Z\u0080-\u00FF_] ;
DIGIT : [0-9] ;
COLON: ':' ;
STRING: '"' (ESC|.)*? '"' ;
ESC: '\\"' | '\\\\' ;
NIL: 'nil';