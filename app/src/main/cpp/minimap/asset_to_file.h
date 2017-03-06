#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <stdio.h>
#include <asm/errno.h>

#ifndef METAGENOMIX_ASSET_TO_FILE_H
#define METAGENOMIX_ASSET_TO_FILE_H

#endif //METAGENOMIX_ASSET_TO_FILE_H


static int android_read(void *cookie, char *buf, int size) {
    return AAsset_read((AAsset *) cookie, buf, size);
}

static int android_write(void *cookie, const char *buf, int size) {
    return EACCES; // Can't write to assets folder
}

static fpos_t android_seek(void *cookie, fpos_t offset, int whence) {
    return AAsset_seek((AAsset *) cookie, offset, whence);
}

static int android_close(void *cookie) {
    if (cookie == NULL) return 0;
    AAsset_close((AAsset *) cookie);
    return 0;
}