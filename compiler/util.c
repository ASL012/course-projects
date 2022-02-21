/****************************************************/
/* File: util.c                                     */
/* Utility function implementation                  */
/* for the C- compiler                              */
/* Compiler Construction: Principles and Practice   */
/****************************************************/

#include "global.h"
#include "util.h"

/* Procedure printToken prints a token and its lexeme to the listing file */
void printToken( TokenType token, const char* tokenString )
{
    switch (token)
    {
        case IF:
        case ELSE:
        case INT:
        case RETURN:
        case VOID:
        case WHILE:
            fprintf(listing,
                    "reserved word: %s\n",tokenString);
            break;
        case ASSIGN: fprintf(listing,"=\n"); break;
        case LT: fprintf(listing,"<\n"); break;
        case LE: fprintf(listing,"<=\n"); break;
        case GT: fprintf(listing,">\n"); break;
        case GE: fprintf(listing,">=\n"); break;
        case EQ: fprintf(listing,"==\n"); break;
        case NE: fprintf(listing,"!=\n"); break;
        case LPAREN: fprintf(listing,"(\n"); break;
        case RPAREN: fprintf(listing,")\n"); break;
        case LSBRACKET: fprintf(listing,"[\n"); break;
        case RSBRACKET: fprintf(listing,"]\n"); break;
        case LBRACE: fprintf(listing,"{\n"); break;
        case RBRACE: fprintf(listing,"}\n"); break;
        case SEMI: fprintf(listing,";\n"); break;
        case COMMA: fprintf(listing,",\n"); break;
        case PLUS: fprintf(listing,"+\n"); break;
        case MINUS: fprintf(listing,"-\n"); break;
        case TIMES: fprintf(listing,"*\n"); break;
        case OVER: fprintf(listing,"/\n"); break;
        case ENDFILE: fprintf(listing,"EOF\n"); break;
        case NUM:
            fprintf(listing,
                    "NUM, val= %s\n",tokenString);
            break;
        case ID:
            fprintf(listing,
                    "ID, name= %s\n",tokenString);
            break;
        case ERROR:
            fprintf(listing,
                    "ERROR: %s\n",tokenString);
            break;
        default: /* should never happen */
            fprintf(listing,"Unknown token: %d\n",token);
    }
}


/* Function newStmtNode creates a new statement
 * node for syntax tree construction
 */
/*TreeNode * newStmtNode(StmtKind kind)
{ TreeNode * t = (TreeNode *) malloc(sizeof(TreeNode));
    int i;
    if (t==NULL)
        fprintf(listing,"Out of memory error at line %d\n",lineno);
    else {
        for (i=0;i<MAXCHILDREN;i++) t->child[i] = NULL;
        t->sibling = NULL;
        t->nodekind = StmtK;
        t->kind.stmt = kind;
        t->lineno = lineno;
    }
    return t;
}
*/

/* Function newExpNode creates a new expression
 * node for syntax tree construction
 */
/*TreeNode * newExpNode(ExpKind kind)
{ TreeNode * t = (TreeNode *) malloc(sizeof(TreeNode));
    int i;
    if (t==NULL)
        fprintf(listing,"Out of memory error at line %d\n",lineno);
    else {
        for (i=0;i<MAXCHILDREN;i++) t->child[i] = NULL;
        t->sibling = NULL;
        t->nodekind = ExpK;
        t->kind.exp = kind;
        t->lineno = lineno;
        t->type = Void;
    }
    return t;
}
*/

TreeNode * newNode(NodeKind kind)
{
    TreeNode *t = (TreeNode *)malloc(sizeof(TreeNode));
    int i;
    if (t == NULL)
        fprintf(listing, "Out of memory error at line %d\n", lineno);
    else {
        for (i = 0; i < MAXCHILDREN; i++)
            t->child[i] = NULL;
        t->sibling = NULL;
        t->nodeKind = kind;
        t->lineno = lineno;
        if (kind == OpK || kind == IntK || kind == IdK)
            t->type = INT;
        if (kind == IdK)
            t->attr.name = "";
        if (kind == ConstK)
            t->attr.val = 0;
    }
    return t;
}


/* Function copyString allocates and makes a new copy of an existing string */
char * copyString(char * s)
{
    int n;
    char * t;
    if (s==NULL) return NULL;
    n = strlen(s)+1;
    t = malloc(n);
    if (t==NULL)
        fprintf(listing,"Out of memory error at line %d\n",lineno);
    else strcpy(t,s);
    return t;
}

/* Variable indentno is used by printTree to store current number of spaces to indent */
static int indentno = 0;

/* macros to increase/decrease indentation */
#define INDENT indentno+=2
#define UNINDENT indentno-=2

/* printSpaces indents by printing spaces */
static void printSpaces(void)
{
    int i;
    for (i=0;i<indentno;i++)
        fprintf(listing," ");
}

/* procedure printTree prints a syntax tree to the
 * listing file using indentation to indicate subtrees
 */
void printTree(TreeNode * tree)
{
    int i = 0;
    INDENT;
    while (tree != NULL) {
        printSpaces();
        switch (tree->nodeKind) {
            case VoidK:
                fprintf(listing, "VoidK\n");
                break;
            case IntK:
                fprintf(listing, "IntK\n");
                break;
            case IdK:
                fprintf(listing, "IdK: %s\n", tree->attr.name);
                break;
            case ConstK:
                fprintf(listing, "ConstK:%d\n", tree->attr.val);
                break;
            case Var_DeclK:
                fprintf(listing, " Var_DeclK\n");
                break;
            case Arry_DeclK:
                fprintf(listing, "Arry_DeclK\n");
                break;
            case FunK:
                fprintf(listing, "FuncK\n");
                break;
            case ParamsK:
                fprintf(listing, "ParamsK\n");
                break;
            case ParamK:
                fprintf(listing, "ParamK\n");
                break;
            case CompK:
                fprintf(listing, "CompK\n");
                break;
            case Selection_StmtK:
                fprintf(listing, "If\n");
                break;
            case Iteration_StmtK:
                fprintf(listing, "While\n");
                break;
            case Return_StmtK:
                fprintf(listing, "Return\n");
                break;
            case AssignK:
                fprintf(listing, "Assign\n");
                break;
            case OpK:
                fprintf(listing, "Op: ");
                printToken(tree->attr.op, "\0");
                break;
            case Arry_ElemK:
                fprintf(listing, "Arry_ElemK\n");
                break;
            case CallK:
                fprintf(listing, "CallK\n");
                break;
            case ArgsK:
                fprintf(listing, "ArgsK\n");
                break;
            default:
                fprintf(listing, "Unknown Node kind\n");
                break;
        }
        for (i = 0; i < MAXCHILDREN; i++) {
            //if(tree->child[i]!=NULL)
            printTree(tree->child[i]);
        }
        tree = tree->sibling;
    }
    UNINDENT;
}

