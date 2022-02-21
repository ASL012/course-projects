/****************************************************/
/* File: parse.h                                    */
/* The parser interface for the C- compiler         */
/* Compiler Construction: Principles and Practice   */
/****************************************************/

#include "global.h"

#ifndef COMPILER_PARSE_H
#define COMPILER_PARSE_H

/* Function parse returns the newly constructed syntax tree */
TreeNode * parse(void);

#endif //COMPILER_PARSE_H
