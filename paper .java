import java.util.*;

class paper
{
	public static void main(String ahes[])
	{
		// Getting Input

		Scanner sc = new Scanner(System.in);
		encryption per = new encryption();
		System.out.println("Enter the message: ");
		String s=sc.nextLine();

		int l=s.length();
		int a[]=new int[l];

		for(int i=0;i<l;i++)
		{
			a[i]=s.charAt(i);
			System.out.println("ASCII values are: "+a[i]);
		}

		//Perceptron Neural Network

		float output[]=per.network_function(a,l);
		System.out.println("The output of perceptron network is :");
		for(int i=0;i<l;i++)
		{
			System.out.println(output[i]);
		}

		//XOR data with key
		System.out.println("Enter the encryption key: ");	
		int public_key=sc.nextInt();

		int output_of_xor[]=per.xor(output,public_key,l);
		for(int i=0;i<l;i++)
		{
			System.out.println("This is output of XOR: "+output_of_xor[i]);
		}

		//Convertion from float to int and then to binary.

		int length_of_binary = 12;
		String strBinary[] = per.toBinary_function(output_of_xor,l,length_of_binary);

		for(int i=0;i<l;i++)
		{
			System.out.println(strBinary[i]);
		}

		//Grouping 4 bits and 1st stage of encryption

		String input_01, input_02, input_03, key_01, key_03, key_02;
		String encryption_01[] = new String[l];

		for(int i=0;i<l;i++)
		{
			input_01=strBinary[i].substring(0,4);
			input_02=strBinary[i].substring(4,8);
			input_03=strBinary[i].substring(8,12);

			key_01=per.encrypt(input_01);
			key_02=per.encrypt(input_02);
			key_03=per.encrypt(input_03);

			encryption_01[i]=key_01+key_02+key_03;
			System.out.println(encryption_01[i]);
		}

		// Complementing the DNA code
		String input_04,input_05,input_06,input_07,input_08,input_09;
		String key_04,key_05,key_06,key_07,key_08,key_09;

		for(int i=0;i<l;i++)
		{
			input_04=encryption_01[i].substring(0,1);
			input_05=encryption_01[i].substring(1,2);
			input_06=encryption_01[i].substring(2,3);
			input_07=encryption_01[i].substring(3,4);
			input_08=encryption_01[i].substring(4,5);
			input_09=encryption_01[i].substring(5,6);

			key_04=per.dna_complement(input_04);
			key_05=per.dna_complement(input_05);
			key_06=per.dna_complement(input_06);
			key_07=per.dna_complement(input_07);
			key_08=per.dna_complement(input_08);
			key_09=per.dna_complement(input_09);

			encryption_01[i]="";
			encryption_01[i]=key_04+key_05+key_06+key_07+key_08+key_09;
			System.out.println("After complementing DNA code: "+encryption_01[i]);

		}

		//Final Decimal Conversion

		String ab,cd,ef, output_01,output_02,output_03;
		String final_output="";
		for(int i=0;i<l;i++)
		{
			ab=encryption_01[i].substring(0,2);
			cd=encryption_01[i].substring(2,4);
			ef=encryption_01[i].substring(4,6);

			output_01=per.to_decimal(ab);
			output_02=per.to_decimal(cd);
			output_03=per.to_decimal(ef);

			encryption_01[i]=output_01+output_02+output_03;
			final_output=final_output+encryption_01[i];
		}
		final_output.trim();

		System.out.println("Encrypted Message: "+final_output);


		//Decryption Starts

		decryption dec = new decryption();

		//1. Converting decimal to DNA code

		int length_of_encrypted_message = final_output.length();


		//original_text_length is length_of_encrypted_message/6  => n 

		int n = length_of_encrypted_message/6;
		String decryption_01[] =new String[n];
		int begin_index=0,end_index=6;
		String code_01,code_02,code_03;
		String decimal_01,decimal_02,decimal_03;
		String cipher_text[]=new String[n];
		for(int i=0;i<n;i++)
		{
			cipher_text[i]=final_output.substring(begin_index,end_index);
			begin_index=begin_index+6;
			end_index=end_index+6;
			System.out.println("This is cipher text: "+cipher_text[i]);

		}
		for(int i=0;i<n;i++)
		{
			decimal_01=cipher_text[i].substring(0,2);
			decimal_02=cipher_text[i].substring(2,4);
			decimal_03=cipher_text[i].substring(4,6);

			code_01=dec.to_dna_code(decimal_01);
			code_02=dec.to_dna_code(decimal_02);
			code_03=dec.to_dna_code(decimal_03);
			cipher_text[i]=null;
			cipher_text[i]=code_01+code_02+code_03;
			System.out.println(cipher_text[i]);

		}

		//2. Changing DNA code to its complement

		String decrypt_input_01,decrypt_input_02,decrypt_input_03,decrypt_input_04,decrypt_input_05,decrypt_input_06;
		String decrypt_key_01,decrypt_key_02,decrypt_key_03,decrypt_key_04,decrypt_key_05,decrypt_key_06;
		for(int i=0;i<n;i++)
		{
			decrypt_input_01=cipher_text[i].substring(0,1);
			decrypt_input_02=cipher_text[i].substring(1,2);
			decrypt_input_03=cipher_text[i].substring(2,3);
			decrypt_input_04=cipher_text[i].substring(3,4);
			decrypt_input_05=cipher_text[i].substring(4,5);
			decrypt_input_06=cipher_text[i].substring(5,6);

			decrypt_key_01=dec.to_dna_complement(decrypt_input_01);
			decrypt_key_02=dec.to_dna_complement(decrypt_input_02);
			decrypt_key_03=dec.to_dna_complement(decrypt_input_03);
			decrypt_key_04=dec.to_dna_complement(decrypt_input_04);
			decrypt_key_05=dec.to_dna_complement(decrypt_input_05);
			decrypt_key_06=dec.to_dna_complement(decrypt_input_06);

			decryption_01[i]=decrypt_key_01+decrypt_key_02+decrypt_key_03+decrypt_key_04+decrypt_key_05+decrypt_key_06;
			System.out.println("After the process of complementing: "+decryption_01[i]);

		}

		//3. Converting DNA code to binary

		String group_01,group_02,group_03;
		String value_01,value_02,value_03;
		String decryption_02[]=new String[n];
		for(int i=0;i<n;i++)
		{
			group_01=decryption_01[i].substring(0,2);
			group_02=decryption_01[i].substring(2,4);
			group_03=decryption_01[i].substring(4,6);

			value_01=dec.dna_to_binary(group_01);
			value_02=dec.dna_to_binary(group_02);
			value_03=dec.dna_to_binary(group_03);

			decryption_02[i]=value_01+value_02+value_03;
			System.out.println("After Converting DNA code to binary: "+decryption_02[i]);
		}


		//4. Converting binary to integer

		int decrypted_ascii[]=dec.binary_to_decimal(decryption_02,n);
		for(int i=0;i<decrypted_ascii.length;i++)
		{
			System.out.println("Decrypted values are: "+decrypted_ascii[i]);
		}

		//5. Applying key to the decrypted integer value

		System.out.println("Enter decryption key: ");
		int decryption_key=sc.nextInt();
		int decryption_xor[]=dec.decrypt_xor(decrypted_ascii,decryption_key,n);


		//6. Converting integer to ASCII values using neural network

		float ascii_value[]=dec.inverse_network_function(decryption_xor,n);
		int final_output_01[]=new int[n];
		for(int i=0;i<n;i++)
		{
			final_output_01[i]=(int)ascii_value[i];
			System.out.println("Decrypted ASCII values are: "+final_output_01[i]);
		}

		//7. Decrypting original message

		String decrypted_message="";
		for(int i=0;i<n;i++)
		{
			decrypted_message=decrypted_message+Character.toString((char)final_output_01[i]);
		}
		System.out.println("The Decrypted origianl message is: "+decrypted_message);






	}//main
}//class

class encryption
{
	

	float[] network_function(int a[], int l)
	{
		float updated_x[] = new float[4];
		int bias=0;
		float hidden_layer_output = 0;
		float combination_output = 0;
		float output[]=new float[l];
		
		int x[]={4,6,8,10};
		float w[]={0.5f,1.0f,1.0f,0.5f};


		for(int i=0;i<4;i++)
		{
			updated_x[i] = x[i]*w[i];
			System.out.println("Updated_x value :"+updated_x[i]);
			hidden_layer_output = hidden_layer_output + updated_x[i];
			System.out.println("hidden_layer_output :"+hidden_layer_output);
		}


		for (int i=0;i<l;i++)
		{
			bias = a[i];
			combination_output = hidden_layer_output+bias;
			output[i] = combination_output;
		}

		return output;


	}

	int[] xor(float original_text[],int public_key,int l)
	{
		int t=0;
		int xor_output[]=new int[l];
		for(int i=0;i<l;i++)
		{
			t=(int)original_text[i];
			xor_output[i]=(t | public_key) & (~t | ~public_key);
		}
		return xor_output;

	}

	String[] toBinary_function(int output[],int l, int length_of_binary)
	{
		int t=0;
		String b[]=new String[l];
		for(int i=0;i<l;i++)
		{
			if(length_of_binary>0)
			{
				t=output[i];
				b[i]=String.format("%" + length_of_binary + "s", Integer.toBinaryString(t)).replaceAll(" ", "0");
			}

		}
		return b;
	} 

	


	String encrypt(String input)
	{
		String key="";
		if(input.equals("0000"))
			{
				key="AA";
			}
			if(input.equals("0001"))
			{
				key="AC";
			}
			if(input.equals("0010"))
			{
				key="AG";
			}
			if(input.equals("0011"))
			{
				key="AT";
			}
			if(input.equals("0100"))
			{
				key="CA";
			}
			if(input.equals("0101"))
			{
				key="CC";
			}
			if(input.equals("0110"))
			{
				key="CG";
			}
			if(input.equals("0111"))
			{
				key="CT";
			}
			if(input.equals("1000"))
			{
				key="GA";
			}
			if(input.equals("1001"))
			{
				key="GC";
			}
			if(input.equals("1010"))
			{
				key="GG";
			}
			if(input.equals("1011"))
			{
				key="GT";
			}
			if(input.equals("1100"))
			{
				key="TA";
			}
			if(input.equals("1101"))
			{
				key="TC";
			}
			if(input.equals("1110"))
			{
				key="TG";
			}
			if(input.equals("1111"))
			{
				key="TT";
			}

			return key;

	}

	String dna_complement(String input)
	{
		String key="";
		if(input.equals("A"))
		{
			key="C";
		}
		if(input.equals("C"))
		{
			key="G";
		}
		if(input.equals("G"))
		{
			key="T";
		}
		if(input.equals("T"))
		{
			key="A";
		}
		return key;
	}

	String to_decimal(String input)
	{
		String key="";
		if(input.equals("AA"))
		{
			key="00";
		}
		if(input.equals("AC"))
		{
			key="01";
		}
		if(input.equals("AG"))
		{
			key="02";
		}
		if(input.equals("AT"))
		{
			key="03";
		}
		if(input.equals("CA"))
		{
			key="04";
		}
		if(input.equals("CC"))
		{
			key="05";
		}
		if(input.equals("CG"))
		{
			key="06";
		}
		if(input.equals("CT"))
		{
			key="07";
		}
		if(input.equals("GA"))
		{
			key="08";
		}
		if(input.equals("GC"))
		{
			key="09";
		}
		if(input.equals("GG"))
		{
			key="10";
		}
		if(input.equals("GT"))
		{
			key="11";
		}
		if(input.equals("TA"))
		{
			key="12";
		}
		if(input.equals("TC"))
		{
			key="13";
		}
		if(input.equals("TG"))
		{
			key="14";
		}
		if(input.equals("TT"))
		{
			key="15";
		}

		return key;

	}


}

class decryption extends encryption
{
	String to_dna_code(String input)
	{
		String key=null;
		if(input.equals("00"))
		{
			key="AA";
		}
		if(input.equals("01"))
		{
			key="AC";
		}
		if(input.equals("02"))
		{
			key="AG";
		}
		if(input.equals("03"))
		{
			key="AT";
		}
		if(input.equals("04"))
		{
			key="CA";
		}
		if(input.equals("05"))
		{
			key="CC";
		}
		if(input.equals("06"))
		{
			key="CG";
		}
		if(input.equals("07"))
		{
			key="CT";
		}
		if(input.equals("08"))
		{
			key="GA";
		}
		if(input.equals("09"))
		{
			key="GC";
		}
		if(input.equals("10"))
		{
			key="GG";
		}
		if(input.equals("11"))
		{
			key="GT";
		}
		if(input.equals("12"))
		{
			key="TA";
		}
		if(input.equals("13"))
		{
			key="TC";
		}
		if(input.equals("14"))
		{
			key="TG";
		}
		if(input.equals("15"))
		{
			key="TT";
		}

		return key;
	}

	String to_dna_complement(String input)
	{
		String key="";
		if(input.equals("C"))
		{
			key="A";
		}
		if(input.equals("G"))
		{
			key="C";
		}
		if(input.equals("T"))
		{
			key="G";
		}
		if(input.equals("A"))
		{
			key="T";
		}

		return key;

	}

	String dna_to_binary(String input)
	{
		String key="";
		if(input.equals("AA"))
			{
				key="0000";
			}
			if(input.equals("AC"))
			{
				key="0001";
			}
			if(input.equals("AG"))
			{
				key="0010";
			}
			if(input.equals("AT"))
			{
				key="0011";
			}
			if(input.equals("CA"))
			{
				key="0100";
			}
			if(input.equals("CC"))
			{
				key="0101";
			}
			if(input.equals("CG"))
			{
				key="0110";
			}
			if(input.equals("CT"))
			{
				key="0111";
			}
			if(input.equals("GA"))
			{
				key="1000";
			}
			if(input.equals("GC"))
			{
				key="1001";
			}
			if(input.equals("GG"))
			{
				key="1010";
			}
			if(input.equals("GT"))
			{
				key="1011";
			}
			if(input.equals("TA"))
			{
				key="1100";
			}
			if(input.equals("TC"))
			{
				key="1101";
			}
			if(input.equals("TG"))
			{
				key="1110";
			}
			if(input.equals("TT"))
			{
				key="1111";
			}

			return key;
	}

	int[] binary_to_decimal(String input[],int n)
	{
		int ascii[]=new int[n];
		for(int i=0;i<n;i++)
		{
			ascii[i]=Integer.parseInt(input[i],2);

		}
		return ascii;
	}

	int[] decrypt_xor(int input[],int key,int n)
	{
		int output[]=new int[n];
		for(int i=0;i<n;i++)
		{
			output[i]=(input[i] | key) & (~input[i] | ~key);
		}
		return output;

	}

	float[] inverse_network_function(int input[],int n)
	{
		float updated_x[] = new float[4];
		int bias=0;
		float hidden_layer_output = 0;
		float combination_output = 0;
		float output[]=new float[n];
		
		int x[]={4,6,8,10};
		float w[]={0.5f,1.0f,1.0f,0.5f};


		for(int i=0;i<4;i++)
		{
			updated_x[i] = x[i]*w[i];
			System.out.println("Updated_x value :"+updated_x[i]);
			hidden_layer_output = hidden_layer_output + updated_x[i];
			System.out.println("hidden_layer_output :"+hidden_layer_output);
		}

		for (int i=0;i<n;i++)
		{
			bias = input[i];
			combination_output = bias-hidden_layer_output;
			output[i] = combination_output;
		}

		return output;

		
	}




}

