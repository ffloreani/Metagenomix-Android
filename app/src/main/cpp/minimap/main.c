#include <unistd.h>
#include <stdio.h>
#include <sys/resource.h>
#include <jni.h>
#include <android/log.h>
#include "minimap.h"

void liftrlimit() {
#ifdef __linux__
    struct rlimit r;
    getrlimit(RLIMIT_AS, &r);
    r.rlim_cur = r.rlim_max;
    setrlimit(RLIMIT_AS, &r);
#endif
}

JNIEXPORT void JNICALL
Java_com_metagenomix_android_activities_ConversionActivity_map(JNIEnv *env, jobject this,
                                                               jstring sample_path,
                                                               jstring database_path,
                                                               jstring output_path);

int mapper(const char *database_fn, const char *sample_fn, const char *output_fn) {
    mm_mapopt_t opt;
    int i, k = 15, w = -1, b = MM_IDX_DEF_B, n_threads = 3, keep_name = 1;
    int tbatch_size = 100000000;
    uint64_t ibatch_size = 4000000000ULL;
    float f = 0.001;
    bseq_file_t *fp = 0;
    FILE* output_fp = fopen(output_fn, "w+");

    liftrlimit();
    mm_realtime0 = realtime();
    mm_mapopt_init(&opt);

    if (w < 0) w = (int) (.6666667 * k + .499);

    fp = bseq_open(database_fn); // DATABASE FASTA FILE
    for (;;) {
        mm_idx_t *mi = 0;
        if (!bseq_eof(fp))
            mi = mm_idx_gen_output(fp, w, k, b, tbatch_size, n_threads, ibatch_size, keep_name, output_fp);
        if (mi == 0) break;
        mm_idx_set_max_occ(mi, f);
        mm_map_file_output(mi, sample_fn, &opt, n_threads, tbatch_size, output_fp); // ENVIRONMENT SAMPLE FASTA FILE
        mm_idx_destroy(mi);
    }
    if (fp) bseq_close(fp);

    __android_log_print(ANDROID_LOG_VERBOSE, "Main mapper",
                        "\n[M::%s] Real time: %.3f sec; CPU: %.3f sec\n", __func__,
                        realtime() - mm_realtime0, cputime());
    return 0;
}

JNIEXPORT void JNICALL
Java_com_metagenomix_android_activities_ConversionActivity_map(JNIEnv *env, jobject this,
                                                               jstring sample_path,
                                                               jstring database_path,
                                                               jstring output_path) {
    const char *database_fn = (*env)->GetStringUTFChars(env, database_path, NULL);
    const char *sample_fn = (*env)->GetStringUTFChars(env, sample_path, NULL);
    const char *output_fn = (*env)->GetStringUTFChars(env, output_path, NULL);

    mapper(database_fn, sample_fn, output_fn);

    (*env)->ReleaseStringUTFChars(env, sample_path, sample_fn);
    (*env)->ReleaseStringUTFChars(env, database_path, database_fn);
    (*env)->ReleaseStringUTFChars(env, output_path, output_fn);
}