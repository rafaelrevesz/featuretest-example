package com.example.featuretest;


import io.cucumber.core.cli.Main;

public class FeatureTest {

    public static void main(final String[] runtimeArgs) {
        Main.main(concatArgs(new String[] {
                "classpath:featuretest",
                "--glue", "com.example.featuretest",
                "--snippets", "camelcase",
        }, runtimeArgs));
    }

    private static String[] concatArgs(final String[] a, final String[] b) {
        final int aLen = a.length;
        final int bLen = b.length;
        final String[] c = new String[aLen + bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

}
