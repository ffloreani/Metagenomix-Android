#include <unistd.h>
#include <stdio.h>
#include <sys/resource.h>
#include <jni.h>
#include <android/log.h>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include "asset_to_file.h"
#include "minimap.h"

#define MM_VERSION "0.2-r124-dirty"

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
                                                               jobject assetManager,
                                                               jstring samplePath,
                                                               jstring databasePath);

int main(FILE* database_fp, FILE* sample_fp) {
    mm_mapopt_t opt;
    int i, k = 15, w = -1, b = MM_IDX_DEF_B, n_threads = 3, keep_name = 1;
    int tbatch_size = 100000000;
    uint64_t ibatch_size = 4000000000ULL;
    float f = 0.001;
    bseq_file_t *fp = 0;

    liftrlimit();
    mm_realtime0 = realtime();
    mm_mapopt_init(&opt);

    if (w < 0) w = (int) (.6666667 * k + .499);

    fp = bseq_file_open(database_fp); // DATABASE FASTA FILE
    for (;;) {
        mm_idx_t *mi = 0;
        if (!bseq_eof(fp))
            mi = mm_idx_gen(fp, w, k, b, tbatch_size, n_threads, ibatch_size, keep_name);
        if (mi == 0) break;
        mm_idx_set_max_occ(mi, f);
        mm_map_file_pointer(mi, sample_fp, &opt, n_threads, tbatch_size); // ENVIRONMENT SAMPLE FASTA FILE
        mm_idx_destroy(mi);
    }
    if (fp) bseq_file_close(fp);

    __android_log_print(ANDROID_LOG_VERBOSE, "Main",
                        "\n[M::%s] Real time: %.3f sec; CPU: %.3f sec\n", __func__,
                        realtime() - mm_realtime0, cputime());
    return 0;
}

JNIEXPORT void JNICALL
Java_com_metagenomix_android_activities_ConversionActivity_map(JNIEnv *env, jobject this,
                                                               jobject assetManager,
                                                               jstring samplePath,
                                                               jstring databasePath) {
    AAssetManager *mgr = AAssetManager_fromJava(env, assetManager);
    if (NULL == mgr) return;

    const char *databaseName = (*env)->GetStringUTFChars(env, databasePath, NULL);
    const char *sampleName = (*env)->GetStringUTFChars(env, samplePath, NULL);
    AAsset *database_asset = AAssetManager_open(mgr, databaseName, AASSET_MODE_UNKNOWN);
    AAsset *sample_asset = AAssetManager_open(mgr, sampleName, AASSET_MODE_UNKNOWN);

    FILE *database_fp = funopen(database_asset, android_read, android_write, android_seek, android_close);
    FILE *sample_fp = funopen(sample_asset, android_read, android_write, android_seek, android_close);

    main(database_fp, sample_fp);

    (*env)->ReleaseStringUTFChars(env, samplePath, sampleName);
    (*env)->ReleaseStringUTFChars(env, databasePath, databaseName);
}
