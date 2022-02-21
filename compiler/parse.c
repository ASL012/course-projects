/****************************************************/
/* File: parse.c                                    */
/* The parser implementation for the C- compiler    */
/* Compiler Construction: Principles and Practice   */
/****************************************************/

#include "global.h"
#include "util.h"
#include "scan.h"
#include "parse.h"

static TokenType token; /* holds current token */

/* function prototypes for recursive calls */
static TreeNode * declaration_list(void);
static TreeNode * declaration(void);
static TreeNode * var_declaration(void);
static TreeNode * fun_declaration(void);
static TreeNode * params(void);
static TreeNode * param_list(TreeNode * k);
static TreeNode * param(TreeNode * k);
static TreeNode * compound_stmt(void);
static TreeNode * local_declarations(void);
static TreeNode * statement_list(void);
static TreeNode * statement(void);
static TreeNode * expression_stmt(void);
static TreeNode * selection_stmt(void);
static TreeNode * iteration_stmt(void);
static TreeNode * return_stmt(void);
static TreeNode * expression(void);
static TreeNode * var(void);
static TreeNode * simple_expression(TreeNode * k);
static TreeNode * additive_expression(TreeNode * k);
static TreeNode * term(TreeNode * k);
static TreeNode * factor(TreeNode * k);
static TreeNode * call(TreeNode * k);
static TreeNode * args(void);
static TreeNode * arg_list(void);

static void syntaxError(char * message)
{   fprintf(listing,"\n>>> ");
    fprintf(listing,"Syntax error at line %d: %s",lineno,message);
    Error = TRUE;
}


static void match(TokenType expected)
{ if (token == expected) token = getToken();
    else {
        syntaxError("unexpected token -> ");
        printToken(token,tokenString);
        fprintf(listing,"      ");
    }
}


TreeNode * declaration_list(void)
{

    TreeNode * t = declaration();
    TreeNode * p = t;
    while ((token != RPAREN) && (token != IF) && (token != RETURN) &&  //?????????  token!=ENDFILE) && (token!=END) &&(token!=ELSE) && (token!=UNTIL))
           (token != WHILE) && (token != ENDFILE))
    {
        TreeNode * q;
        q = declaration();
        if (q != NULL)
        {
            if (t == NULL)
            {
                t = p = q;
            }
            else
            {
                p->sibling = q;
                p = q;
            }
        }
    }
    return t;
}


TreeNode * declaration(void)
{
    TreeNode * t = NULL;
    TreeNode * p = NULL;
    TreeNode * q = NULL;
    TreeNode * s = NULL;
    TreeNode * m = newNode(Arry_DeclK);
    if (token == INT)
    {
        p = newNode(IntK);
        match(INT);
    }
    else if (token == VOID)
    {
        p = newNode(VoidK);
        match(VOID);
    }
    else
    {
        syntaxError("unexpected token -> ");
    }

    if ((p != NULL) && (token == ID))
    {
        q = newNode(IdK);
        q->attr.name = copyString(tokenString);
        match(ID);

        switch (token) {
            case LPAREN: //函数声明
                t = newNode(FunK);
                if (t != NULL)
                {
                    t->child[0] = p;
                    t->child[1] = q;
                }
                match(LPAREN);
                if (t != NULL)
                    t->child[2] = params();
                match(RPAREN);
                if (t != NULL)
                    t->child[3] = compound_stmt();
                break;
            case LSBRACKET: //数组
                t = newNode(Var_DeclK);
                match(LSBRACKET);
                s = newNode(ConstK);
                s->attr.val = atoi(tokenString);
                match(NUM);
                m->child[0] = q;
                m->child[1] = s;
                if (t != NULL)
                {
                    t->child[0] = p;
                    t->child[1] = m;
                }
                match(RSBRACKET);
                match(SEMI);
                break;
            case SEMI:
                t = newNode(Var_DeclK);
                if (t != NULL)
                {
                    t->child[0] = p;
                    t->child[1] = q;
                }
                match(SEMI);
            default:
                syntaxError("unexpected token -> ");
                break;
        }
    }
    else {
        syntaxError("unexpected token -> ");
    }
    return t;
}


TreeNode * params(void) {

    TreeNode * t = newNode(ParamsK);
    TreeNode * p = NULL;
    switch (token) {
        case INT:
            t->child[0] = param_list(p);
            break;
        case VOID:
            p = newNode(VoidK);
            match(VOID);
            if (token == RPAREN)
                if (t != NULL)
                    t->child[0] = p;
                else
                {
                    t->child[0] = param_list(p);
                }
            break;
        default:
            syntaxError("unexpected token -> ");
            break;
    }
    return t;

}


TreeNode * param_list(TreeNode * k)
{

    TreeNode * t = param(k);
    TreeNode * p = t;
    k = NULL;
    while (token == COMMA)
    {
        TreeNode * q = NULL;
        match(COMMA);
        q = param(k);
        if (q != NULL) {
            if (t == NULL)
                t = p = q;
            else {
                p->sibling = q;
                p = q;
            }
        }
    }
    return t;
}


TreeNode * param(TreeNode * k)
{


    TreeNode * t = newNode(ParamK);
    TreeNode * p = NULL;
    TreeNode * q = NULL;

    if ((k == NULL) || (token == INT))
    {
        p = newNode(IntK);
        match(INT);
    }
    else if (k != NULL)
        p = k;
    if (p != NULL)
    {
        t->child[0] = p;
        if (token == ID)
        {
            q = newNode(IdK);
            q->attr.name = copyString(tokenString);
            t->child[1] = q;
            match(ID);
        }
        else {
            syntaxError("unexpected token -> ");
        }

        if ((token == LSBRACKET) && (t->child[1] != NULL))
        {
            match(LSBRACKET);
            //t->child[2] = newNode(IdK);
            t->child[2] = newNode(IdK);
            match(RSBRACKET);
        }
        else
        {
            return t;
        }
    }
    else {
        syntaxError("unexpected token -> ");
    }
    return t;
}


TreeNode * compound_stmt(void)
{
    TreeNode * t = newNode(CompK);
    match(LBRACE);
    if (t != NULL)
        t->child[0] = local_declarations();
    if (t != NULL)
        t->child[1] = statement_list();
    match(RBRACE);
    return t;
}


TreeNode * local_declarations(void)
{
    TreeNode * t = NULL;
    TreeNode * q = NULL;
    TreeNode * p = NULL;
    while (token == INT || token == VOID)
    {
        p = newNode(Var_DeclK);
        if (token == INT)
        {
            TreeNode * q1 = newNode(IntK);
            p->child[0] = q1;
            match(INT);
        }
        else if (token == VOID)
        {
            TreeNode * q1 = newNode(VoidK);
            p->child[0] = q1;
            match(INT);
        }
        if ((p != NULL) && (token == ID))
        {
            TreeNode * q2 = newNode(IdK);
            q2->attr.name = copyString(tokenString);
            p->child[1] = q2;
            match(ID);

            if (token == LSBRACKET)
            {
                TreeNode * q3 = newNode(Var_DeclK);
                TreeNode * q4 = newNode(ConstK);
                q3->child[0] = q2;
                //	TreeNode * q4 = newNode(ConstK);
                q4->attr.val = atoi(tokenString);
                q3->child[1] = q4;
                match(LSBRACKET);
                p->child[1] = q3;
                match(RSBRACKET);
                match(SEMI);
            }
            else if (token == SEMI)
            {
                match(SEMI);
            }
            else
            {
                match(SEMI);
            }
        }
        else
        {
            syntaxError("unexpected token -> ");
        }
        if (p != NULL)
        {
            if (t == NULL)
                t = q = p;
            else
            {
                q->sibling = p;
                q = p;
            }
        }
    }
    return t;

}


TreeNode * statement_list(void)
{
    TreeNode * t = NULL;
    TreeNode * p = t;
    while ((token == LBRACE) || (token == ID) ||
           (token == IF) || (token == WHILE) || (token == NUM) || (token == RETURN))
    {
        TreeNode * q = statement();//...
        if (q != NULL)
        {
            if (t == NULL)
            {
                t = p = q;
            }
            else
            {
                p->sibling = q;
                p = q;
            }
        }

    }
    return t;
}


TreeNode * statement(void)
{
    TreeNode * t = NULL;
    switch (token)
    {
        case ID:
        case SEMI:
        case LPAREN:
        case NUM:
            t = expression_stmt();
            break;
        case LBRACE:
            t = compound_stmt();
            break;
        case IF:
            t = selection_stmt();
            break;
        case WHILE:
            t = iteration_stmt();
            break;
        case RETURN:
            t = return_stmt();
            break;
        default:
            syntaxError("unexpected token -> ");
            token = getToken();
            break;
    }
    return t;
}


TreeNode * expression_stmt(void)
{

    TreeNode * t = NULL;
    if (token == SEMI)
    {
        match(SEMI);
        return t;
    }
    else if (token == ID || token == LPAREN || token == NUM)
    {
        t = expression();//...
        match(SEMI);
    }
    else
    {
        syntaxError("unexpected token -> ");
    }
    return t;

}


TreeNode * selection_stmt(void)
{

    TreeNode * t = newNode(Selection_StmtK);
    match(IF);
    match(LPAREN);
    if (t != NULL)
        t->child[0] = expression();
    match(RPAREN);
    if (t != NULL)
        t->child[1] = statement();
    if (token == ELSE)
    {
        match(ELSE);
        if (t != NULL)
            t->child[2] = statement();
    }
    return t;
}


TreeNode * iteration_stmt(void)
{
    TreeNode * t = newNode(Iteration_StmtK);
    match(WHILE);
    match(LPAREN);
    if (t != NULL)
        t->child[0] = expression();
    match(RPAREN);
    if (t != NULL)
        t->child[1] = statement();
    return t;
}


TreeNode * return_stmt(void)
{

    TreeNode * t = newNode(Return_StmtK);
    if (token == RETURN)
    {
        match(RETURN);
        if (token == ID || token == LPAREN || token == NUM)
        {
            if (t != NULL)
                t->child[0] = expression();
        }
        match(SEMI);
    }
    return t;
}


TreeNode * expression(void)
{

    TreeNode * t = var();
    if (t == NULL)//不是以ID开头，只能是simple_expression情况
    {
        t = simple_expression(t);
    }
    else//以ID开头，可能是赋值语句，或simple_expression中的var和call类型的情况
    {
        TreeNode * p = NULL;
        if (token == ASSIGN)
        {
            p = newNode(AssignK);
            p->attr.name = copyString(tokenString);
            match(ASSIGN);
            p->child[0] = t;
            p->child[1] = expression();
            return p;
        }
        else
        {
            t = simple_expression(t);
        }
    }
    return t;
}


TreeNode * var(void)
{

    TreeNode * t = NULL;
    TreeNode * p = NULL;
    TreeNode * q = NULL;
    if (token == ID)
    {
        p = newNode(IdK);
        p->attr.name = copyString(tokenString);
        match(ID);
        if (token == LSBRACKET)
        {
            match(LSBRACKET);
            q = expression();
            match(RSBRACKET);

            t = newNode(Arry_ElemK);
            t->child[0] = p;
            t->child[1] = q;
        }
        else
        {
            t = p;
        }
    }
    return t;
}


TreeNode * simple_expression(TreeNode * k)
{

    TreeNode * t = additive_expression(k);
    k = NULL;
    if ((token == LE) || (token == LT) || (token == GT) || (token == GE) || (token == EQ) || (token == NE))
    {
        TreeNode * q = newNode(OpK);
        q->attr.op = token;
        q->child[0] = t;
        t = q;
        match(token);
        t->child[1] = additive_expression(k);
        return t;
    }
    return t;

}


TreeNode * additive_expression(TreeNode * k)
{

    TreeNode * t = term(k);
    k = NULL;
    while ((token == PLUS) || (token == MINUS))
    {
        TreeNode * p = newNode(OpK);
        if (p != NULL)
        {
            p->child[0] = t;
            p->attr.op = token;
            match(token);
            p->child[1] = term(k);
            t = p;
        }
    }
    return t;

}


TreeNode * term(TreeNode * k)
{


    TreeNode * t = factor(k);
    k = NULL;
    while ((token == TIMES) || (token == OVER))
    {
        TreeNode * p = newNode(OpK);
        if (p != NULL)
        {
            p->child[0] = t;
            p->attr.op = token;
            t = p;
            match(token);
            p->child[1] = factor(k);
        }
    }
    return t;

}


TreeNode * factor(TreeNode * k)
{
    TreeNode * t = NULL;
    if (k != NULL) //call或var的情况
    {
        if (token == LPAREN && k->nodeKind != Arry_ElemK)
            t = call(k);
        else
            t = k;
    }
    else
    {
        switch (token)
        {
            case LPAREN:
                match(LPAREN);
                t = expression();
                match(RPAREN);
                break;
            case ID:
                //match(ID);
                k = var();
                if ((token == LPAREN) && (k->nodeKind != Arry_ElemK))
                {
                    t = call(k);
                }
                else t = k;
                break;
            case NUM:
                t = newNode(ConstK);
                if ((t != NULL) && (token == NUM))
                    t->attr.val = atoi(tokenString);
                match(NUM);
                break;
            default:
                syntaxError("unexpected token -> ");
                token = getToken();
                break;
        }
    }
    return t;

}


TreeNode * call(TreeNode * k)
{


    TreeNode * t = newNode(CallK);
    TreeNode * p = NULL;
    if (k != NULL) {
        t->child[0] = k;
        match(LPAREN);
        if (token == RPAREN)
        {
            match(RPAREN);
        }
        else
        {
            t->child[1] = args();
            match(RPAREN);
        }
    }
    else
    {
        if (token == ID)
        {
            p = newNode(IdK);
            p->attr.name = copyString(tokenString);
            match(ID);
        }
        t->child[0] = p;
        match(RPAREN);
        t->child[1] = args();
        match(RPAREN);
    }
    return t;
}


TreeNode * args(void)
{

    TreeNode * t = newNode(ArgsK);
    TreeNode * s = NULL;
    TreeNode * p = NULL;
    if (token == ID || token == LPAREN || token == NUM)
    {
        s = expression();
        p = s;
        while (token == COMMA)
        {
            TreeNode * q;
            match(COMMA);
            q = expression();
            if (q != NULL)
            {
                if (s == NULL)
                {
                    s = p = q;
                }
                else
                {
                    p->sibling = q;
                    p = q;
                }
            }
        }
    }
    t->child[0] = s;

    return t;

}

/****************************************/
/* the primary function of the parser   */
/****************************************/
/* Function parse returns the newly
 * constructed syntax tree
 */
TreeNode * parse(void)
{   TreeNode * t;
    token = getToken();
    t = declaration_list();
    if (token!=ENDFILE)
        syntaxError("Code ends before file\n");
    return t;
}
