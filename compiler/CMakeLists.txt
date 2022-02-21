cmake_minimum_required(VERSION 3.16)
project(compiler C)

set(CMAKE_C_STANDARD 99)

add_executable(compiler main.c global.h scan.h util.h util.c scan.c parse.h parse.c)