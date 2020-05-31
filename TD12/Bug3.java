class Bug3
{
    
    public static void main(String[] args)
    {
   	 System.out.println("Entrez un nombre n =  ");

        int n = Integer.parseInt(System.console().readLine());
        int facto = 1;
        for (int i=1; i<=n; i=i+1)
            {
                facto = facto * i;
            }
        System.out.println(n+"! =  " + facto);
    }
   
}
