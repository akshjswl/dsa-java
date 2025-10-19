import java.util.*;

public class QuadraticOptimizer {
    
    static class Term {
        Map<String, Integer> coeffs = new HashMap<>();
        
        Term() {
            coeffs.put("xx", 0);
            coeffs.put("xy", 0);
            coeffs.put("yy", 0);
            coeffs.put("x", 0);
            coeffs.put("y", 0);
            coeffs.put("const", 0);
        }
    }
    
    static class ParseResult {
        Map<String, Integer> poly;
        int pos;
        
        ParseResult(Map<String, Integer> poly, int pos) {
            this.poly = poly;
            this.pos = pos;
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine().trim();
        System.out.println(solve(expr));
        sc.close();
    }
    
    static int solve(String expr) {
        Term term = parseAndExpand(expr);
        
        // If all coefficients are zero except const, it's fully evaluated
        if (term.coeffs.get("xx") == 0 && term.coeffs.get("xy") == 0 && 
            term.coeffs.get("yy") == 0 && term.coeffs.get("x") == 0 && 
            term.coeffs.get("y") == 0) {
            return 0;
        }
        
        int expandedOps = countOperations(term);
        int minOps = tryFactorizations(term);
        
        return Math.min(expandedOps, minOps);
    }
    
    static Term parseAndExpand(String expr) {
        expr = expr.replace(" ", "");
        
        if (!expr.contains("x") && !expr.contains("y")) {
            try {
                int val = evaluateConstant(expr);
                Term t = new Term();
                t.coeffs.put("const", val);
                return t;
            } catch (Exception e) {}
        }
        
        return expandExpression(expr);
    }
    
    static int evaluateConstant(String expr) {
        return evalExpr(expr, 0)[0];
    }
    
    static int[] evalExpr(String expr, int pos) {
        int[] left = evalTerm(expr, pos);
        int val = left[0];
        pos = left[1];
        
        while (pos < expr.length() && expr.charAt(pos) == '+') {
            pos++;
            int[] right = evalTerm(expr, pos);
            val += right[0];
            pos = right[1];
        }
        return new int[]{val, pos};
    }
    
    static int[] evalTerm(String expr, int pos) {
        int[] left = evalFactor(expr, pos);
        int val = left[0];
        pos = left[1];
        
        while (pos < expr.length() && expr.charAt(pos) == '*') {
            pos++;
            int[] right = evalFactor(expr, pos);
            val *= right[0];
            pos = right[1];
        }
        return new int[]{val, pos};
    }
    
    static int[] evalFactor(String expr, int pos) {
        if (expr.charAt(pos) == '(') {
            pos++;
            int[] result = evalExpr(expr, pos);
            pos = result[1] + 1;
            return new int[]{result[0], pos};
        }
        
        int start = pos;
        while (pos < expr.length() && Character.isDigit(expr.charAt(pos))) {
            pos++;
        }
        return new int[]{Integer.parseInt(expr.substring(start, pos)), pos};
    }
    
    static Term expandExpression(String expr) {
        List<String> tokens = tokenize(expr);
        ParseResult result = parseExprPoly(tokens, 0);
        
        Term term = new Term();
        for (Map.Entry<String, Integer> e : result.poly.entrySet()) {
            String key = e.getKey();
            int val = e.getValue();
            
            if (key.isEmpty()) {
                term.coeffs.put("const", val);
            } else if (key.equals("x")) {
                term.coeffs.put("x", val);
            } else if (key.equals("y")) {
                term.coeffs.put("y", val);
            } else if (key.equals("xx")) {
                term.coeffs.put("xx", val);
            } else if (key.equals("xy")) {
                term.coeffs.put("xy", val);
            } else if (key.equals("yy")) {
                term.coeffs.put("yy", val);
            }
        }
        return term;
    }
    
    static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        while (i < expr.length()) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                while (j < expr.length() && Character.isDigit(expr.charAt(j))) j++;
                tokens.add(expr.substring(i, j));
                i = j;
            } else if (c == 'x' || c == 'y') {
                tokens.add(String.valueOf(c));
                i++;
            } else if (c == '*' || c == '+' || c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
                i++;
            } else {
                i++;
            }
        }
        return tokens;
    }
    
    static ParseResult parseExprPoly(List<String> tokens, int pos) {
        ParseResult left = parseTermPoly(tokens, pos);
        Map<String, Integer> result = new HashMap<>(left.poly);
        pos = left.pos;
        
        while (pos < tokens.size() && tokens.get(pos).equals("+")) {
            pos++;
            ParseResult right = parseTermPoly(tokens, pos);
            result = addPoly(result, right.poly);
            pos = right.pos;
        }
        return new ParseResult(result, pos);
    }
    
    static ParseResult parseTermPoly(List<String> tokens, int pos) {
        ParseResult left = parseFactorPoly(tokens, pos);
        Map<String, Integer> result = new HashMap<>(left.poly);
        pos = left.pos;
        
        while (pos < tokens.size() && tokens.get(pos).equals("*")) {
            pos++;
            ParseResult right = parseFactorPoly(tokens, pos);
            result = multiplyPoly(result, right.poly);
            pos = right.pos;
        }
        return new ParseResult(result, pos);
    }
    
    static ParseResult parseFactorPoly(List<String> tokens, int pos) {
        String token = tokens.get(pos);
        
        if (token.equals("(")) {
            pos++;
            ParseResult result = parseExprPoly(tokens, pos);
            pos = result.pos + 1;
            return new ParseResult(result.poly, pos);
        } else if (token.equals("x")) {
            Map<String, Integer> map = new HashMap<>();
            map.put("x", 1);
            return new ParseResult(map, pos + 1);
        } else if (token.equals("y")) {
            Map<String, Integer> map = new HashMap<>();
            map.put("y", 1);
            return new ParseResult(map, pos + 1);
        } else {
            Map<String, Integer> map = new HashMap<>();
            map.put("", Integer.parseInt(token));
            return new ParseResult(map, pos + 1);
        }
    }
    
    static Map<String, Integer> addPoly(Map<String, Integer> p1, Map<String, Integer> p2) {
        Map<String, Integer> result = new HashMap<>(p1);
        for (Map.Entry<String, Integer> e : p2.entrySet()) {
            result.put(e.getKey(), result.getOrDefault(e.getKey(), 0) + e.getValue());
        }
        return result;
    }
    
    static Map<String, Integer> multiplyPoly(Map<String, Integer> p1, Map<String, Integer> p2) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> e1 : p1.entrySet()) {
            for (Map.Entry<String, Integer> e2 : p2.entrySet()) {
                String key = multiplyTerms(e1.getKey(), e2.getKey());
                int val = e1.getValue() * e2.getValue();
                result.put(key, result.getOrDefault(key, 0) + val);
            }
        }
        return result;
    }
    
    static String multiplyTerms(String t1, String t2) {
        char[] combined = (t1 + t2).toCharArray();
        Arrays.sort(combined);
        return new String(combined);
    }
    
    static int countOperations(Term term) {
        // Count operations: each term needs its multiplications + additions between terms
        int ops = 0;
        List<Integer> termOps = new ArrayList<>();
        
        // x^2 term
        if (term.coeffs.get("xx") != 0) {
            int c = Math.abs(term.coeffs.get("xx"));
            if (c == 1) {
                termOps.add(1); // just x*x
            } else {
                termOps.add(2); // c*x*x
            }
        }
        
        // xy term
        if (term.coeffs.get("xy") != 0) {
            int c = Math.abs(term.coeffs.get("xy"));
            if (c == 1) {
                termOps.add(1); // just x*y
            } else {
                termOps.add(2); // c*x*y
            }
        }
        
        // y^2 term
        if (term.coeffs.get("yy") != 0) {
            int c = Math.abs(term.coeffs.get("yy"));
            if (c == 1) {
                termOps.add(1); // just y*y
            } else {
                termOps.add(2); // c*y*y
            }
        }
        
        // x term
        if (term.coeffs.get("x") != 0) {
            int c = Math.abs(term.coeffs.get("x"));
            if (c == 1) {
                termOps.add(0); // just x
            } else {
                termOps.add(1); // c*x
            }
        }
        
        // y term
        if (term.coeffs.get("y") != 0) {
            int c = Math.abs(term.coeffs.get("y"));
            if (c == 1) {
                termOps.add(0); // just y
            } else {
                termOps.add(1); // c*y
            }
        }
        
        // constant term
        if (term.coeffs.get("const") != 0) {
            termOps.add(0); // just a number
        }
        
        // Sum operations within terms
        for (int op : termOps) {
            ops += op;
        }
        
        // Add operations for additions between terms
        if (termOps.size() > 1) {
            ops += termOps.size() - 1;
        }
        
        return ops;
    }
    
    static int tryFactorizations(Term term) {
        int cxx = term.coeffs.get("xx");
        int cxy = term.coeffs.get("xy");
        int cyy = term.coeffs.get("yy");
        int cx = term.coeffs.get("x");
        int cy = term.coeffs.get("y");
        int cconst = term.coeffs.get("const");
        
        int minOps = Integer.MAX_VALUE;
        
        // Form 1: (a1*x + b1)(a2*x + b2)
        if (cxx != 0 && cxy == 0 && cyy == 0 && cy == 0) {
            for (int a1 = -100; a1 <= 100; a1++) {
                if (a1 == 0 || cxx % a1 != 0) continue;
                int a2 = cxx / a1;
                for (int b1 = -100; b1 <= 100; b1++) {
                    if (cconst != 0 && b1 != 0 && cconst % b1 != 0) continue;
                    int b2 = (b1 == 0) ? (cconst == 0 ? 0 : Integer.MAX_VALUE) : cconst / b1;
                    if (b2 != Integer.MAX_VALUE && b1 * b2 == cconst && a1 * b2 + a2 * b1 == cx) {
                        int ops = countFactoredOps(a1, b1, a2, b2, 1);
                        minOps = Math.min(minOps, ops);
                    }
                }
            }
        }
        
        // Form 2: (a1*x + b1)(a2*y + b2)
        if (cxy != 0 && cxx == 0 && cyy == 0) {
            for (int a1 = -100; a1 <= 100; a1++) {
                if (a1 == 0 || cxy % a1 != 0) continue;
                int a2 = cxy / a1;
                for (int b1 = -100; b1 <= 100; b1++) {
                    if (cconst != 0 && b1 != 0 && cconst % b1 != 0) continue;
                    int b2 = (b1 == 0) ? (cconst == 0 ? 0 : Integer.MAX_VALUE) : cconst / b1;
                    if (b2 != Integer.MAX_VALUE && b1 * b2 == cconst && a1 * b2 == cx && a2 * b1 == cy) {
                        int ops = countFactoredOps(a1, b1, a2, b2, 2);
                        minOps = Math.min(minOps, ops);
                    }
                }
            }
        }
        
        // Form 3: (a1*x + b1*y)(a2*x + b2)
        if (cxx != 0 && cyy == 0) {
            for (int a1 = -100; a1 <= 100; a1++) {
                if (a1 == 0 || cxx % a1 != 0) continue;
                int a2 = cxx / a1;
                for (int b1 = -100; b1 <= 100; b1++) {
                    if (cy != 0 && b1 != 0 && cy % b1 != 0) continue;
                    int b2 = (b1 == 0) ? (cy == 0 ? 0 : Integer.MAX_VALUE) : cy / b1;
                    if (b2 != Integer.MAX_VALUE && b1 * b2 == cy && a1 * b2 + a2 * b1 == cxy && a2 * b2 == cconst) {
                        int ops = countFactoredOps(a1, b1, a2, b2, 3);
                        minOps = Math.min(minOps, ops);
                    }
                }
            }
        }
        
        return minOps;
    }
    
    static int countFactoredOps(int a1, int b1, int a2, int b2, int form) {
        int ops = 0;
        
        // First factor: (a1*x + b1) or (a1*x + b1*y)
        if (form == 3) {
            // (a1*x + b1*y)
            if (a1 != 1 && a1 != 0) ops++; // a1*x
            if (b1 != 1 && b1 != 0) ops++; // b1*y
            if (b1 != 0) ops++; // addition
        } else {
            // (a1*x + b1) or (a1*y + b1)
            if (a1 != 1 && a1 != 0) ops++; // a1*var
            if (b1 != 0) ops++; // addition
        }
        
        // Second factor: (a2*x + b2) or (a2*y + b2)
        if (a2 != 1 && a2 != 0) ops++; // a2*var
        if (b2 != 0) ops++; // addition
        
        // Multiplication between factors
        ops++;
        
        return ops;
    }
}