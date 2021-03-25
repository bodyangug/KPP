package com.company;

public class Test_12switch {

        public int calcHeight(Size size){
           switch(size){
                case S:
                    return 18;
                case M:
                    return  20;
                case L:
                    return  25;
                case XL:
                    return 30;
            };
           return 0;
        }
        enum Size {S, M, L, XL}

}
