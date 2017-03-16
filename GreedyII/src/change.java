import java.util.Arrays;
import java.util.Scanner;

public class change {

	public static void main(String[] args) {
		Scanner scanny = new Scanner(System.in);
		int loop = scanny.nextInt();
		int c = 0;
		boolean[] memo = new boolean[1000000000];
		for(int x = 0; x < loop; x++){
			c++;
			int loop2 = scanny.nextInt();
			int[] list = new int[loop2];
			Arrays.fill(memo, false);
			for(int y = 0; y < loop2; y++){
				list[y] = scanny.nextInt();
				memo[y] = true;
			}
			int max = 1000000000;
			int max2 = 10;
			for(int a = 0; a < 100000; a++){
				
				if(!works(a,list,memo)){
					System.out.println("Set #" + c + ": " + a);
					
					break;
				}
			
				
			}
	
			
		}
		
		
	/*
		for(int x = 0; x < 10; x++){
			System.out.print(memo[x] + " ");
		}
		*/
		
	}
	
	/*public static boolean works(int sum, int[] list, int ind, boolean[] memo){
		if(ind >= list.length || sum < 0){
			return sum==0?true:false;
		}
		if(sum == 0){
			return true;
		}
		if(!memo[sum]){
			return false;
		}
		
	
		return works(sum-list[ind], list, ind+1,memo) || works(sum,list,ind+1,memo);
		
		
	}*/
	
	public static boolean works(int sum, int[] list, boolean[] dp){
		//System.out.println(sum);
		if(sum == 0){
			dp[0] = true;
			return true;
		}
		for(int x = 0; x < list.length; x++){
			dp[list[x]] = true;
		}
		
			for(int y = 0; y < list.length; y++){
			
				if(sum-list[y] == 0){
					dp[sum] = true;
					return true;
				}
				else if(sum-list[y]>0){
					
					//System.out.println("Sum " + sum + " listy " + list[y]);
					if(dp[sum-list[y]]){
						//System.out.println("YES");
						dp[sum] = true;
						return true;
					}
				}
			//	return false;
			
			
		}
		return false;
		
		
		
	}
	
	
	
}

/*
 100
6
12 8 1 2 4 100
3
1 2 3
6
3 1 3 2 3 3
30
1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576 2097152 4194304 8388608 16777216 33554432 67108864 134217728 268435456 463129089
30
1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576 2097152 4194304 8388608 16777216 33554432 67108864 134217728 268435456 463129088
30
1 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576 2097152 4194304 8388608 16777217 33554432 67108864 134217728 268435456 463129088
1
1
1
2
1
1000000000
2
1 1
2
2 1
2
3 1
3
4 2 1
3
2 1 3
3
5 1 3
3
1 1 1
3
2 2 2
3
5 1 2
30
29 21 27 26 7 1 3 4 6 30 12 16 18 17 11 19 20 2 28 22 25 5 24 10 23 9 8 13 15 14
30
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
30
1 1464 41234 1 1450 10410 13920 29058 30188 21175 16749 4 3 21936 13347 46764 22312 41768 39142 1 23060 924 15751 15725 12845 14279 47516 24565 11699 46569
30
4190 10 32396 38041 31749 34634 1 31080 4 1 9279 44305 16 44923 5 3127 35541 5 7723 39 20059 43010 26958 7765 9564 2 2 24125 1 11011
30
95 12 870 1 61 2 2272 59 55206 37031 1924 55098 5767 24310 11 89 17004 222 32770 24 3 2 56394 29729 212 981 490 5 3 55175
30
15 66 9 18261 46430 5897 197 65 47827 21987 31944 22363 12855 1 1 4 14958 35818 4770 5493 2 9165 4501 26331 14492 10 4 37312 20170 27
30
40697 22858 37727 5 1 41510 9777 1 17101 42576 33106 20789 49729 1 1 14456 30929 36745 37144 28034 21498 30600 6823 8040 2043 37148 13022 36184 42894 2
30
10180 24584 33628 11284 26311 19762 27825 45038 27529 10122 13447 1 6763 9035 46626 5974 2747 12551 40089 484 26641 4302 23791 16512 44508 21400 39032 28998 7579 4177
30
8312 6281 9 1 45791 27784 37199 40302 3 12637 9 33905 6 573 27659 14546 13487 12881 12 2 40992 45565 5488 12763 1087 1 19 30581 36663 9815
30
307 702 446 70 4504 72 30184 47607 33909 3 22804 17 1 40591 427 5 48467 143 14562 3 2 33738 33658 3907 10 39 43 4 5 26
30
2 25626 38950 44566 16 31 10 37 40106 6623 228 37909 3 15994 21890 15 46 1 39533 21537 33541 7555 33572 19670 22360 29131 42207 95 5 31134
30
33284 16817 39462 1 1 3182 430 25165 14001 6065 14283 3848 12988 29895 42618 37551 34320 41769 9374 3454 6985 18602 42587 6281 30242 49023 38486 12626 36406 42838
30
12931 49642 3 14632 10 23425 1 2 23 38362 2 5838 47208 17163 33678 1 47176 14462 5 40384 27882 1 20368 64 15521 44209 2929 49318 39699 27
30
557 149274 49099 100 122369 23002 129836 7 159 274 1 13780 704 865 45 2 3351 456 5 25 118457 3236 5 2 55 556 4761 21 2045 136528
30
1052 25310 39388 35657 46996 1 9784 28017 17513 33304 20632 2243 30355 12204 44636 418 48649 33313 21351 30194 3865 2572 2708 32357 49611 38586 1 49115 10245 640
30
4 9 2 4405 17 68126 249 1731 6293 101802 955 297 8972 20902 5242 63271 122 4 456 57 1 100515 12 77949 49 3529 5 2 97 111
30
20735 12 3 12976 1 10279 169 5 892 6860 32092 87 14458 21258 37340 28 40275 2 46405 45469 29395 34098 1714 30615 46844 442 56 3 1 26215
30
34497 11 40203 9 1 43318 6419 1182 48518 47323 20443 13399 22161 1 48746 44 831 44 49019 10 33994 3 5 17479 39535 1 4 21286 30013 74
30
1 18928 279 15080 19051 7658 24448 171 2817 13064 17681 27675 29925 17784 6594 1 2 47507 3 49432 14256 19891 14497 30711 28862 18330 32690 35735 1393 30721
30
74621 2 1 62 250 662 1104 711 114 4831 1 9 787 29 16 2077 16123 65342 56980 1753 34 31 180 7089 1851 77159 5 1698 1 14
30
39838 16010 100 50889 1158 45808 19 497 62 1 5782 30 2 163 2 1 1 1277 1 26 31 175 7 30576 36 92 133 419 6 1403
30
16371 3 2 1473 68 42208 65 4114 408 2229 67395 541 1 226 723 247745 1 227771 45775 6 83 28 10420 1 10 17 1 8094 198 1779
30
6767 1 4 17651 336 23891 1 6 12 32246 24541 23218 49 91 7467 32 5 173 21719 2 45036 13 6587 15837 37921 36669 62 12989 33 4
30
1759 5149 86 319 184 100 26 7 15 28 41438 700 15 137604 12055 1485 1227 1 1 80 17876 1 30066 412 1710 3 4 122557 1 1
30
26109 108 90 30020 8 11574 1 23023 45608 39591 44381 28113 3 34258 4812 21 5656 52 4 6468 10 1 25631 18129 31041 29404 19545 1 5 2706
30
8197 150 536 50850 4 1 69 1392 27235 39 848 5 1 44835 3 19257 13 21947 180 1951 19 974 37007 14072 101 2 47 2 85 22200
30
2 19330 3457 18892 11 13 6 2067 2 20 107 132 1 36973 474 460 96 57672 23487 52 75 6 36651 2 13791 84 1174 58482 29151 777
30
20 35072 1 49710 38988 2 1 40101 40841 32365 37665 34433 1 1 8747 13758 6955 12188 1 22684 38303 15605 34070 45352 47579 5 19851 43167 9 20101
30
258 7 7861 152 17213 2 33 3 52529 28 1514 48 12 34 1239 1 147 54482 173 291 62 9442 19 28 25392 11596 43 3347 21 68
30
20687 14373 15860 43534 11586 2294 2077 7250 5660 13574 19223 1 34398 27506 29242 13155 7022 6892 38439 49208 48101 3462 41666 23859 19465 22424 29471 5998 39642 10253
30
35771 20 28078 56 5 6522 11 3637 34656 2 15 29145 40575 35925 28165 1 8575 4563 47615 47027 21365 43170 36024 44684 1 4 9943 3 26 45969
30
668 63698 712 414 178 3544 22 9 2 50250 3 12 34 22929 5369 1 21 52867 69 14 850 1564 4 37 26392 963 10 54 2 17
30
19884 38610 46341 40088 10318 2 1 25854 30748 31109 38017 40497 7 31462 3 2 48664 43833 15 9120 3658 36007 3 25929 39741 21975 3 7462 7974 23639
30
34784 9796 21319 1 25520 29725 1 19124 6004 27882 669 25177 29650 28459 12479 46920 10840 39199 41465 35143 34205 3 37164 48577 40060 1 31637 10436 3247 44156
30
23703 424 436 164 1 125 36030 40790 4 37885 647 26552 25687 18323 24 30691 5 1828 1 21 28 36682 20046 2 43847 9 6535 706 11 79
30
42398 10 31351 34930 35798 2 12408 49545 4456 33140 10287 27676 42337 28067 1 41751 42095 1 36609 44077 23 5 47436 39443 10833 42129 8 8031 25763 20078
30
1908 8486 99 34821 34984 10 6 31508 73 26012 10 10212 11900 18591 37069 10 27463 6462 36678 29 26101 5 11 1 1 3637 44691 45627 2 38581
30
8583 33032 5 1234 17737 12457 6 48365 1038 41501 1 39318 2 1 32754 23049 7461 4355 29483 14072 35800 992 14605 8 34130 17000 3 14518 20367 27914
30
4996 21 1050 2518 26137 2724 7 86110 49 2224 297 1 7 1 40 111149 286 2521 1001 17484 19 581 98029 120 89 13 269 2 4717 2
30
32089 7015 15182 14 17406 29533 3 11108 28280 1 34450 9530 5 2 4 39269 10 38467 10133 8 20 29 2665 20407 32284 20 20943 39423 22652 93
30
49417 255 17178 43 19483 6748 34535 23813 94 42270 108 37486 19 8 42219 1 20812 6 1 45120 45 1 1767 49438 80 2 1 39937 46085 5
30
83621 14 590 110 11 181 4724 67314 439 322 62741 49 37 440 21 450 1 2 1 10876 3859 59680 89 1424 20248 13 5 8363 8 1
30
1 24680 1 17296 17750 1 16588 8836 3977 1249 1 7 23265 1 47861 2 46720 25237 38001 40482 38512 19215 10469 26767 38685 9357 4837 23591 1408 6854
30
39657 3181 39952 5833 7961 4219 12487 846 33868 2 32538 21742 39904 7994 37334 6225 1 32380 42123 36900 17629 44900 48068 22585 3677 26257 39367 1 31738 30583
30
177 23729 32142 187 1 11966 46 49 41192 21 32 11 92 28 423 1 228 1 25225 10 3 28702 533 7 30766 2 14 2 49783 14569
30
11226 30517 11 48596 285 8929 41300 5 22072 3867 35472 26407 15847 44922 21198 584 30962 20248 3 2 38168 21604 1 35461 22710 21951 28912 12537 32027 1
30
44392 55795 32282 6077 277 8 6 5 1837 2855 3 661 78 107 56 1623 32 222 1 517 217 3360 534 3 52937 2 66595 5 95 1
30
162893 20324 2 1120 1 1 278 2368 7951 434 22 300 8 2087 7 1329 172436 58 138 20158 1162 1 3 7 29 6152 18630 172269 70166 1
30
17666 8 4 46 183 40605 6 20872 19849 3 25714 25070 2 93 42065 182 193 28 25 12221 13474 47703 1 443 2 412 29373 6618 31409 45479
30
19946 39757 32362 42733 5034 30 4 25304 29 4 4 25504 8 8058 17584 39766 1 43584 10559 2 1 2 45429 3 31189 30856 45464 9606 7 24873
30
2 40 13838 47676 30073 1 13284 12271 16 10799 40313 20631 1826 30193 38696 12 32160 5 2 41068 1 2615 29404 8 30 3 19992 23936 45166 28998
30
1811 4521 12008 26299 35542 18768 490 27718 48573 5781 14305 13497 28869 32594 14602 36015 41782 43879 3440 27425 5707 41822 1 34542 16688 516 28693 37197 40898 3806
30
29 2139 528 69715 57 2 28479 63 1 12 156 132 40955 11 1 10799 13 1563 9 30476 704 100 3291 1 1147 1 56 3152 6 48877
30
2 12 1 182 44936 34 24862 21630 65 164 610 14845 30062 6 29052 14130 397 420 115 3 20 21896 14806 11896 8637 10094 45794 354 10 24398
30
3415 1725 5428 14111 95623 1 98501 103040 93700 30518 68 4 250 3 64222 62 4525 103158 16 82271 72207 566 387 1 1 258 2 13 34 84
30
1 82 21438 26765 249 2 1154 34 2173 146 798 8 5096 58039 748 54247 2 56112 17649 79 1 14588 30934 18627 17 2 19652 808 31 255
30
2 206 662 32 40113 2 3 635 106 1057 2974 103 4 43656 80 57 13 23273 40911 32212 16437 5115 1 24 2 47311 15 23282 258 169
30
1076 10806 458 13 69 1 3 32476 49902 43 55 1 48818 200 24125 338 51218 8 21 11 5228 4 20855 76 22374 26 1 2059 69 6821
30
23036 4330 8 38459 435 1 2 24985 41700 4 1 7895 4 4 40 5 38050 742 17 1191 11228 22332 1318 31991 8832 4058 48065 65 22880 11478
30
46173 33264 24136 6919 661 523 24885 6 34472 2060 2 17937 40840 47442 15403 24852 12714 15130 37827 40982 41861 4 1 35149 13442 45955 43553 38814 1909 20204
30
4468 142815 371 169187 34 4 276 2 16 41 2665 7663 10 9264 48130 1086 31202 2647 245 7076 8783 2 135519 3422 1223 124 1385 48 1 6
30
18847 11803 2 2 1 1 16 2 5 12 1 6 303 37405 12466 41238 1 9 4 152 47 2 189 45 18293 28603 42633 4020 40471 3
30
37848 37584 43930 49269 46130 3 2 20739 407 41754 6367 36224 17127 11666 526 45904 8102 44893 13614 26464 27037 31662 1 19193 27078 10894 9841 41692 466 43239
30
443 185 13 127 25710 223 31 1 1 8 374 5670 17459 935 67779 44 176 4 15 2222 173699 140145 6549 12 6859 1 2527 60 1 24
30
27146 7767 13 38 21935 4537 2 40050 17882 1 7 11 6 28328 19966 2703 15345 52 20 2 13 1841 30282 2 1818 28819 28137 43258 1 29994
30
13862 38370 5 3 1 39964 56 1 39448 2 25972 104 49657 2 31305 27 43736 229 3 1 12 76 46391 4 3134 2867 9109 29762 17722 327
30
223 29 39039 11949 14 8478 234 36283 47 16619 6 1 33 2 716 1786 1 1381 15073 1 6 56 1690 2 43225 56 184 56381 8025 10
30
37734 110 19321 8007 1029 6 47553 29815 24667 1 3 37 7378 25826 7395 3 4977 41517 35918 1029 120 16 30723 46655 2 6 22 39978 40432 34
30
29593 30507 13337 19192 37811 32769 33318 1 1925 24922 2598 22386 2007 23243 37520 12148 4633 13897 43708 5301 2 31461 14912 3067 36690 43572 3702 15122 2 28511
30
25258 45590 46005 9 25 768 25470 9 1 2114 36141 19896 23893 668 69 2 6 921 2 3296 2937 22334 42 230 251 4 94 220 13 21732
30
41687 36099 9664 4653 44257 3334 27707 48623 13064 4093 21094 34585 231 13739 40922 20163 43832 5929 18481 43598 29673 2 2 1 11072 27465 12577 38313 1202 554
30
48821 41619 3 38474 3 34714 41324 3099 16 20136 3878 36418 12714 47359 8 18438 11341 17513 19450 3 49997 1 38 1 32 28655 11321 4687 19043 14533
30
27186 4256 25294 20613 23275 4760 1 24051 22344 39290 1 21933 4079 5224 9878 43046 35364 196 24334 27488 32567 18280 11032 21221 34236 9366 47176 41394 12492 29933
30
30 245 45103 4 31150 19 2 86 7 45110 1404 3 19 43 6 15564 95 2 765 56388 42245 1 38293 578 3324 7776 1 31097 504 6
30
423 6 12336 91 1 185 28 199 19729 3 23618 5863 21508 6270 1 21 198 36202 906 8 36946 2 25951 1 6 3 8 37 9 1
30
3 1 116473 49 106 6 4 218 22 1719 8965 28379 383 62 1 3508 2 211 21011 63 2 1 33 9905 120237 1184 2 693 32237 2280
30
37038 8852 144 21 5 25656 1 7998 17896 16 49736 35853 6015 73 49040 40010 4137 11916 5 2 1 21613 2432 5846 30 2 45093 12 41842 51
30
1 15788 8053 28026 5445 20057 1696 15748 2 47129 4 26451 38362 4023 21670 17393 8 6919 10254 18030 24317 27111 11 37698 10663 5844 39356 25797 6551 14
30
21244 47187 36117 42405 2985 21300 26745 14420 2 25274 36578 39986 8404 42354 1493 29865 31930 21444 9245 4582 6830 33008 28643 39858 26751 15389 46114 49405 682 1
30
43497 29263 28703 31044 13 1 10452 22356 16838 18250 35318 7277 577 34101 3 43751 45120 10 2 24600 19 34425 27954 23661 3 4104 28214 2 16527 7026
30
46356 1 2 53651 115 521 39937 13176 1841 30 1 1 9 24 1535 4 20448 44825 7 17 1116 96 24 13585 294 40417 3 277 23121 337
30
24747 1 10331 26413 27063 34267 2134 22027 25092 19103 13479 24370 30077 29073 21257 15894 20682 8435 45034 4 35338 2968 1 2 14334 36780 43457 48871 37997 49538
*/
