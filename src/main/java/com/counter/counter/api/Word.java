/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.counter.counter.api;

/**
 *
 * @author zion
 */
public class Word implements Comparable<Word>{
    String word;
		int count;

		@Override
		public int hashCode()
		{
			return word.hashCode();
		}

		@Override
		public boolean equals(Object obj)
		{
			return word.equals(((Word)obj).word);
		}

		@Override
		public int compareTo(Word b)
		{
			return b.count - count;
}
}
