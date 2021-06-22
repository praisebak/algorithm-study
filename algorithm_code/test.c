int main()
{
    double n;
    double i;

    printf("학생수: ");
    scanf("%f", &n);
    printf("\n");

    double korean[10];
    double english[10];
    double math[10];
    double sum[10];
    double avg_korean;
    double avg_english;
    double avg_math;
    double sum_korean = 0;
    double sum_english = 0;
    double sum_math = 0;


    for (i = 0; i < n; i++)
    {
        printf("[학생 %d]\n", i+1);
        printf(" 점수(국,영,수): ");
        scanf("%f%f%f", &korean[i], &english[i], &math[i]);
        printf("\n");

        sum[i] = korean[i] + english[i] + math[i];

        sum_korean += korean[i];
        sum_english += english[i];
        sum_math += math[i];

    }

    avg_korean = (double)sum_korean / n;
    avg_english = (double)sum_english / n;
    avg_math = (double)sum_math / n;


    printf("<<<<<<<<<<< 성적처리 결과 >>>>>>>>>>>>>\n");
    printf("========================================\n");
    printf("번호\t국어   영어   수학   총점   석차  \n");
    printf("========================================\n");

    for (i = 0; i < n; i++)
    {
        printf("  %d\t %d     %d     %d     %d   ", i + 1, korean[i], english[i], math[i], sum[i]);
        printf("\n");
    }
    printf("========================================\n");
    printf("과목별 평균  %f  %f  %f", avg_korean, avg_english, avg_math);


    return 0;
}