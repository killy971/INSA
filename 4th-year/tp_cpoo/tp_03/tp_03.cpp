// tp_03.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
using namespace std;
#include "chaine.h"
#include <string>


int _tmain(int argc, _TCHAR* argv[])
{
	/**
	 * Tests sur l'egalite
	 * (operateur ==)
	 */
	chaine ch01 = chaine("fdhqsuivorhezup");
	chaine ch02 = chaine("fdhqsuivorhezup");
	chaine ch03 = chaine("fdhqsuivjhgorhezup");
	chaine ch04 = chaine("fdhqsuivdrhezup");
	
	cout << (ch01==ch02) << " " << (ch01==ch03) << " " << (ch01==ch04) << std::endl;
	cout << (ch01=="fdhqsuivorhezup") << " " << (ch01=="fdhqsuivjhgorhezup") << " " << std::endl;
	
	/**
	 * Tests sur la concatenation
	 * (operateur +)
	 */
	chaine ch05 = chaine("aze");
	chaine ch06 = chaine("rty");
	chaine ch07 = chaine("az");
	chaine ch08 = chaine("erty");
	chaine ch09 = chaine(ch05+ch06);
	chaine ch10 = chaine(ch07+ch08);
	
	cout << ch09 << std::endl;
	cout << ch10;
	cout << (ch09==ch10) << std::endl;
	
	/**
	 * Tests sur l'affectation
	 * (operateur =)
	 */
	ch09 = ch06;
	cout << ch09 << std::endl;
	
	chaine ch11 = "ping";
	cout << ch11 << std::endl;
	
	char c1 = 'z';
	chaine ch12 = c1,
	ch1 = c1;
	cout << ch12 << " " << c1 << std::endl;
	
	/**
	 * Tests sur les comparaisons
	 * (operateurs < et >)
	 */
	chaine ch20 = "abc";
	chaine ch21 = "acc";
	chaine ch22 = "abcatpc";
	cout << (ch20<ch21) << (ch21<ch20);
    cout << (ch20<ch22) << (ch22<ch20) << std::endl;

	/**
	 * Test sur l'operateur []
	 */
	cout << chaine (ch20[0]) + ch20[1]<<std::endl;
	
	/**
	 * Test sur l'operateur +=
	 */
	ch20 += ch22;
	cout << ch20 << std::endl;
	
	/**
	 * Tests sur la fonction "sous_chaine"
	 */
	cout << "fonction sous_chaine : " << ch22.sous_chaine('c','p') << std::endl;
	cout << "fonction sous_chaine : " << ch22.sous_chaine(2,5) << std::endl;
	
	/**
	 * Tests sur l'operateur -
	 */
	cout << "operateur - : " << ch22-"bbc" << std::endl;
	cout << "operateur - : " << ch22-"tpc" << std::endl;

	/**
	 * Tests sur l'operateur =-
	 */
	ch22 -= "bbc";
	cout << "operateur -= : " << ch22 << std::endl;
	ch22 -= "tpc";
	cout << "operateur -= : " << ch22 << std::endl;

	/*--------------------------------------------------*/

	/**
	 * On procede desormais aux memes tests avec la classe
	 * de string native.
	 */
	
	/**
	 * Tests sur l'egalite
	 * (operateur ==)
	 */
	string ch23 = string("fdhqsuivorhezup");
	string ch24 = string("fdhqsuivorhezup");
	string ch25 = string("fdhqsuivjhgorhezup");
	string ch26 = string("fdhqsuivdrhezup");
	
	cout << (ch23==ch24) << " " << (ch23==ch25) << " " << (ch23==ch26) << std::endl;
	cout << (ch23=="fdhqsuivorhezup") << " " << (ch23=="fdhqsuivjhgorhezup") << " " << std::endl;
	
	/**
	 * Tests sur la concatenation
	 * (operateur +)
	 */
	string ch27 = string("aze");
	string ch28 = string("rty");
	string ch29 = string("az");
	string ch30 = string("erty");
	string ch31 = string(ch27+ch28);
	string ch32 = string(ch29+ch30);
	
	cout << ch31 << std::endl;
	cout << ch32;
	cout << (ch31==ch32) << std::endl;
	
	/**
	 * Tests sur l'affectation
	 * (operateur =)
	 */
	ch31 = ch28;
	cout << ch31 << std::endl;
	
	string ch33 = "ping";
	cout << ch33 << std::endl;
	
	char c2 = 'z';
//	string ch34 = c2,
//	ch2 = c2;
//	cout << ch34 << " " << c2 << std::endl;
	
	/**
	 * Tests sur les comparaisons
	 * (operateurs < et >)
	 */
	string ch41 = "abc";
	string ch42 = "acc";
	string ch43 = "abcatpc";
	cout << (ch41<ch42) << (ch42<ch41);
    cout << (ch41<ch43) << (ch43<ch41) << std::endl;

	/**
	 * Test sur l'operateur []
	 */
//	cout << string (ch41[0]) + ch41[1]<<std::endl;
	
	/**
	 * Test sur l'operateur +=
	 */
	ch41 += ch43;
	cout << ch41 << std::endl;
	
	/**
	 * Tests sur la fonction "sous_chaine"
	 */
	/*
	cout << ch43.substring('c','p') << std::endl;
	cout << ch43.substring(2,5) << std::endl;
	*/

	/**
	 * Tests sur l'operateur -
	 */
//	cout << "le moins: " << ch43-"tpc" << std::endl;
//	cout << "le moins: " << ch43-"bbc" << std::endl;

	return 0;
}

