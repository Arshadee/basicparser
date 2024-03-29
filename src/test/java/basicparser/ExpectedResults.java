package basicparser;

public class ExpectedResults {
	
	public static String testCase1 =
			"r[r]\n" + 
			"|--a[a]\n" + 
			"|--|--b[b]\n" + 
			"|--|--|--c[c]\n" + 
			"|--|--|--d[d]\n" + 
			"|--e[e]\n";
	
	public static String testCase2 =
			"r[r]\n" + 
			"|--a[a]\n" + 
			"|--|--b[b]\n" + 
			"|--|--c[c]\n" + 
			"|--d[d]\n" + 
			"|--|--e[e]\n" + 
			"|--g[g]\n" + 
			"|--|--h[h]\n" + 
			"|--|--|--i[i]\n" + 
			"|--|--|--|--j[j]\n" + 
			"|--|--|--|--k[k]\n" + 
			"|--|--|--|--l[l]\n" + 
			"|--m[m]\n" + 
			"|--|--n[n]\n" + 
			"|--o[o]\n";
	
	public static String testCase3 = 
			"r[r]\n" + 
			"|--a[a]\n" + 
			"|--b[b]\n" + 
			"|--c[c]\n" + 
			"|--d[d]\n" + 
			"|--|--e[e]\n" + 
			"|--|--f[f]\n" + 
			"|--|--g[g]\n" + 
			"|--|--|--h[h]\n" + 
			"|--|--|--i[i]\n" + 
			"|--|--|--|--j[j]\n";
	
	public static String testCase4 = 
			"r[r]\n" + 
			"|--a[a]\n" + 
			"|--|--b[b]\n" + 
			"|--|--c[c]\n" + 
			"|--|--|--d[d]\n" + 
			"|--|--|--e[e]\n" + 
			"|--|--|--f[f]\n" + 
			"|--|--|--|--g[g]\n" + 
			"|--|--|--|--h[h]\n" + 
			"|--|--|--|--i[i]\n" + 
			"|--|--|--|--j[j]\n" + 
			"|--|--|--|--|--k[k]\n" + 
			"|--|--|--|--|--l[l]\n" + 
			"|--|--|--|--|--m[m]\n" + 
			"|--|--|--|--|--n[n]\n" + 
			"|--|--|--|--|--|--v[v]\n" + 
			"|--|--|--|--|--|--s[s]\n" + 
			"|--|--|--|--|--|--w[w]\n" + 
			"|--|--|--|--|--|--t[t]\n" + 
			"|--|--|--|--|--o[o]\n" + 
			"|--|--|--|--|--|--x[x]\n" + 
			"|--|--|--|--|--|--u[u]\n" + 
			"|--|--|--|--|--|--B[B]\n" + 
			"|--|--|--|--|--|--|--*[*]\n" + 
			"|--|--|--|--|--|--y[y]\n";
	
	public static String testCase5 = 
			"r[r]\n" + 
			"|--a[a]\n" + 
			"|--|--b[b]\n" + 
			"|--|--|--c[c]\n" + 
			"|--|--|--|--d[d]\n" + 
			"|--|--|--e[e]\n" + 
			"|--|--f[f]\n" + 
			"|--g[g]\n";
			
	public static String testCase6 =
			"root[root]\n"+
			"|--branch1[branch1]\n"+
			"|--|--leaf1[leaf1]\n"+
			"|--branch2[branch2]\n"+
			"|--|--leaf2[leaf2]\n"+
			"|--branch3[branch3]\n"+
			"|--|--branch4[branch4]\n"+
			"|--|--|--leaf3[leaf3]\n"+
			"|--|--branch5[branch5]\n"+
			"|--|--|--leaf4[leaf4]\n"+
			"|--|--leaf5[leaf5]\n";
}
