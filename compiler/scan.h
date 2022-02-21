/****************************************************/
/* File: scan.h                                     */
/* The scanner interface for the C- compiler        */
/* Compiler Construction: Principles and Practice   */
/****************************************************/

#include "global.h"

#ifndef COMPILER_SCAN_H
#define COMPILER_SCAN_H

/* MAXTOKENLEN is the maximum size of a token */
#define MAXTOKENLEN 40

/* tokenString array stores the lexeme of each token */
extern char tokenString[MAXTOKENLEN+1];

/* function getToken returns the next token in source file */
TokenType getToken(void);

#endif //COMPILER_SCAN_H
