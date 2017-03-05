#include <zlib.h>
#include <zconf.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <android/log.h>
#include "bseq.h"
#include "kseq.h"

KSEQ_INIT(gzFile, gzread)

#define PATH_MAX 4096

extern unsigned char seq_nt4_table[256];

struct bseq_file_s {
    int is_eof;
    FILE* fp;
    kseq_t *ks;
};

bseq_file_t *bseq_file_open(FILE* database_fp) {
    bseq_file_t *fp;

    //f = fn && strcmp(fn, "-") ? gzopen(fn, "r") : gzdopen(fileno(stdin), "r");
    if (database_fp == 0) return 0;
    fp = (bseq_file_t *) calloc(1, sizeof(bseq_file_t));
    fp->fp = database_fp;
    fp->ks = kseq_init(fp->fp);
    return fp;
}

bseq_file_t *bseq_open(const char *fn) {
    bseq_file_t *fp;

    char buf[PATH_MAX + 1];
    char *res = realpath(fn, buf);
    if (res) {
        __android_log_print(ANDROID_LOG_DEFAULT, "Prototyping", "This source is at: %s.\n", buf);
    }
    FILE* f = fopen(buf, "r");

    //f = fn && strcmp(fn, "-") ? gzopen(fn, "r") : gzdopen(fileno(stdin), "r");
    if (f == 0) return 0;
    fp = (bseq_file_t *) calloc(1, sizeof(bseq_file_t));
    fp->fp = f;
    fp->ks = kseq_init(fp->fp);
    return fp;
}

void bseq_close(bseq_file_t *fp) {
    kseq_destroy(fp->ks);
    gzclose(fp->fp);
    free(fp);
}

void bseq_file_close(bseq_file_t *fp) {
    kseq_destroy(fp->ks);
    fclose(fp->fp);
    free(fp);
}

bseq1_t *bseq_read(bseq_file_t *fp, int chunk_size, int *n_) {
    int size = 0, m, n;
    bseq1_t *seqs;
    kseq_t *ks = fp->ks;
    m = n = 0;
    seqs = 0;
    while (kseq_read(ks) >= 0) {
        bseq1_t *s;
        assert(ks->seq.l <= INT32_MAX);
        if (n >= m) {
            m = m ? m << 1 : 256;
            seqs = (bseq1_t *) realloc(seqs, m * sizeof(bseq1_t));
        }
        s = &seqs[n];
        s->name = strdup(ks->name.s);
        s->seq = strdup(ks->seq.s);
        s->l_seq = ks->seq.l;
        size += seqs[n++].l_seq;
        if (size >= chunk_size) break;
    }
    if (n == 0) fp->is_eof = 1;
    *n_ = n;
    return seqs;
}

int bseq_eof(bseq_file_t *fp) {
    if (fp == NULL) return 1;
    return fp->is_eof;
}
