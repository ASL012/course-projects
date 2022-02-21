/****************************************************/
/* File: util.h                                     */
/* Utility functions for the C- compiler            */
/* Compiler Construction: Principles and Practice   */
/****************************************************/
#include "global.h"

#ifndef COMPILER_UTIL_H
#define COMPILER_UTIL_H

/* Procedure printToken prints a token and its lexeme to the listing file */
void printToken( TokenType, const char* );

/* Function newStmtNode creates a new statement
 * node for syntax tree construction
 */
//TreeNode * newStmtNode(StmtKind);

/* Function newExpNode creates a new expression
 * node for syntax tree construction
 */
//TreeNode * newExpNode(ExpKind);

TreeNode * newNode(NodeKind kind);

/* Function copyString allocates and makes a new
 * copy of an existing string
 */
char * copyString( char * );

/* procedure printTree prints a syntax tree to the
 * listing file using indentation to indicate subtrees
 */
void printTree( TreeNode * );


#endif //COMPILER_UTIL_H
