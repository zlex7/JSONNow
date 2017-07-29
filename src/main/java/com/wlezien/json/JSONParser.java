package com.wlezien.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Pattern;


public class JSONParser {

	private String input;
	private int index;
	private Character[] escapedChars = {'\\','/','b','f','n','r','t','u'};
	HashSet<Character> escapableCharacters = new HashSet<Character>(Arrays.asList(escapedChars));

  public JSON parseInput(String in) throws IOException {
    input = in.trim();
    
		//System.out.println("----------");
    	//  System.out.println("input: " + input);
    	//System.out.println(input.length());
    
    index=0;
    
    /*
    MyJSON data = new MyJSON();
    Pattern p = Pattern.compile("\\\"|\"(?:\"|[^\"])*\"|(:)");
    Matcher m = p.matcher(in);
    while(m.find()){
      m.group();
      int index = m.start();
      System.out.println(index);
    }
    */
		skipWhiteSpace();
		
		char firstChar = input.charAt(index);

		JSON data;

		if(firstChar=='{'){

			index++;
			data=parseRecursive();
			
		}
		else if(firstChar=='['){

			data = new JSON();
			data.setArray("Outer Object",getArray());

		}

		else{

			throw new JSONFormatException("extra characters at beginning of input");
		
		}
		
		/*
		else if(firstChar=='['){

			index++;

			skipWhiteSpace();

			firstChar=input.charAt(index);

			while(firstChar=='['){

				index++;
				skipWhiteSpace();
				firstChar=input.charAt(index);

			}

			if(firstChar=='{'){


			}
		}
		*/
    	
			if(index<input.length()){
				
				if(Pattern.compile("\\S").matcher(input.substring(index)).find()){
					throw new JSONFormatException("extra characters at end of input");
				}
				
			}
			
			return data;


    }


//This method does all of the real work
//It's a little sketchy, but it seems to work fine
  
  public JSON parseRecursive() throws IOException{
	  JSON data = new JSON();
	  skipWhiteSpace();
	  int numEntries = 0;
	    while(input.charAt(index)!='}'){

	    	skipWhiteSpace();

	    	if(numEntries>0){
	    		if(input.charAt(index)!=','){
	    			System.out.println(input.substring(index));
	    			throw new JSONFormatException("no comma in between key:value pair at index " + index);
	    		}
	    		else{
	    			index++;
	    		}
	    	}

	    	skipWhiteSpace();

	    	String key = getString();
	    	
	    	System.out.println("KEY VALUE: " + key);
	    		    		
	    	if(data.hasKey(key)){
				throw new JSONFormatException("duplicate key at index " + index);
			}
	    	
	    //	System.out.println("key: " + key);

	    	skipWhiteSpace();

	    	if(input.charAt(index)!=':'){
	    		//System.out.println(input.substring(index));
	    		throw new JSONFormatException("no colon in between key and value at index " + index);
	    	}
	    	else{
	    		index++;
	    	}

	    	skipWhiteSpace();

				//this is the starting character of the value section of key:value
				//it lets the program know how to handle the value
	    	char beginningValue = input.charAt(index);

	        if(beginningValue=='"'){
	        	String value = getString();
	        	//System.out.println("string value: " + value);
	        	numEntries++;

						//checking for duplicate keys
						if(data.hasKey(key)){
							throw new JSONFormatException("duplicate key at index " + index);
						}
						else{
							data.setString(key,value);
						}
	        }
	        
	        //checking for numbers.
	        else if(Character.isDigit(beginningValue)){
	        	
	        	numEntries++;
	        	double value = getDouble();
	        	data.setNumber(key, value);
	        	
	        }
	        
	        else if(beginningValue=='n' || beginningValue=='t' || beginningValue=='f'){
	        	
	        	 numEntries++;
	        	 
	        	 System.out.println(input.substring(index));
	        	 String keyword = getKeyword(beginningValue);
	        	 
	        	 if(keyword.isEmpty()){
	        		 throw new JSONFormatException("invalid keyword");
	        	 }
	        	 
	        	 else if(keyword.equals("null")){
	        		 data.setObj(key,(JSON)null);
	        	 }
	        	 
	        	 else{
	        		 data.setBoolean(key, Boolean.valueOf(keyword));
	        	 }
	        	 
	        }
	        
	        else if(beginningValue=='['){
	        	
	        	Object[] objects = getArray();
	        	
	        	data.setArray(key,objects);
	        }

	        else if(beginningValue=='{'){
	        	index++;
	        //	System.out.println(input.substring(index));
	        	numEntries++;
				
				data.setObj(key, parseRecursive());
				

	        }
	        

	        skipWhiteSpace();

	      //  System.out.println("index: " + index);
	      }

	    	index++;
	    //System.out.println(data.strings);
	    //System.out.println(data.objects);

	    return data;

  }


	//obviously this is used to skip whitespace in the input
  
  public void skipWhiteSpace(){
	 char next = input.charAt(index);
	 while(Character.isWhitespace(next)){
		 next = input.charAt(++index);
	 }

  }


//this is used to get a string when required
//it also checks for string validity. Correctly escaped characters, etc.
  
  public String getString() throws IOException{
	  int startIndex = index+1;
	  String str="";
	  if(input.charAt(index)=='"'){
  	while(true){
  		index++;
  		if(input.charAt(index)=='\\'){
  			char afterSlash = input.charAt(index+1);
  			if(afterSlash=='\\' || afterSlash=='"' || afterSlash=='n' || afterSlash=='t' || afterSlash=='r' || afterSlash=='f' || afterSlash=='b'){
  				index++;
  			}
  			else if(afterSlash=='u'){
  				 
  			}
  			else{
  				throw new JSONFormatException("invalid escaped character in string at index " + index);
  			}
  		}
  		else if(input.charAt(index)=='"'){
  			str = input.substring(startIndex,index);
  			break;
  			}
  		}
	  }
	  
	  else {
	 // System.out.println(input.substring(index));
	  throw new JSONFormatException("invalid input. please try again. index " + index);
	  }
	  
  	index++;
  	return str;
  }
  
  public double getDouble() throws IOException{
	  
	  
	  int startIndex=index;
	  
	  while(Character.isDigit(input.charAt(index))){
		  
		  index++;
		  
	  }
	  
	  char maybeDecimal = input.charAt(index);
	  
	 if(maybeDecimal=='.'){
		 index++;
		 
		 while(Character.isDigit(input.charAt(index))){
			 
			 index++;
			 
		 }
		 
		 double d = Double.parseDouble(input.substring(startIndex,index));
		 
		 return d;
	 }
	 
	 else{

		 double d = Double.parseDouble(input.substring(startIndex,index));
		 return d;
		 
	 }
	  
	  
  }
  
  public String getKeyword(char start){

	  
	  String keyword;
	  
	  switch(start){
	  
	  case 'n':
		  keyword = input.substring(index,index+4);
		  if(keyword.equals("null")){
			  index+=4;
			  return keyword;
		  }
		  break;
	
	  case 't':
		  keyword = input.substring(index,index+4);
		  if(keyword.equals("true")){
			  index+=4;
			  return keyword;
		  }
		  break;
	  
	  case 'f':
		  keyword = input.substring(index,index+5);
		  if(keyword.equals("false")){
			  index+=5;
			  return keyword;
		  }
		  break;
	  default:
		  System.out.println("keyword not found");
		  return "";
	  }
	  
	  return "";
  }
  
  public Object[] getArray() throws IOException{
	  
	  index++;
	  
	  ArrayList<Object> objects = new ArrayList<Object>(100);
	  
	  int numEntries = 0;
	  
	  skipWhiteSpace();
	  
	  while(input.charAt(index)!=']'){

	    	skipWhiteSpace();

	    	if(numEntries>0){
	    		if(input.charAt(index)!=','){
	    			throw new JSONFormatException("no comma in between values in array at index " + index);
	    		}
	    		else{
	    			index++;
	    		}
	    	}

	    	skipWhiteSpace();

				//this is the starting character of the value
				//it lets the program know how to handle the value
	    	char beginningValue = input.charAt(index);

	        if(beginningValue=='"'){
	        	String value = getString();
	        	
	        	objects.add(value);
	        	
	        	numEntries++;
	        }
	        
	        //checking for numbers.
	        else if(Character.isDigit(beginningValue)){
	        	
	        	double value = getDouble();
	        	
	        	objects.add(value);
	        	
	        }
	        
	        else if(beginningValue=='n' || beginningValue=='t' || beginningValue=='f'){
	        	
	        	 numEntries++;
	        	
	        	 String keyword = getKeyword(beginningValue);
	        	 
	        	 if(keyword.isEmpty()){
	        		 throw new JSONFormatException("invalid keyword");
	        	 }
	        	 
	        	 else{
	        		 objects.add(keyword);
	        	 }
	        	 
	        }
	        
	        else if(beginningValue=='['){
	        	
	        	numEntries++;
	        	objects.add(getArray());
	        	
	        }

	        else if(beginningValue=='{'){
	        	index++;
	        //	System.out.println(input.substring(index));
	        	numEntries++;
				
	        	objects.add(parseRecursive());
	        }
	        

	        skipWhiteSpace();

	      //  System.out.println("index: " + index);
	      }
	  
	  index++;
	  
	  return objects.toArray();
	  
  }

}

