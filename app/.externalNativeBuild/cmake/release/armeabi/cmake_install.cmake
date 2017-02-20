# Install script for directory: /Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp

# Set the install prefix
if(NOT DEFINED CMAKE_INSTALL_PREFIX)
  set(CMAKE_INSTALL_PREFIX "/usr/local")
endif()
string(REGEX REPLACE "/$" "" CMAKE_INSTALL_PREFIX "${CMAKE_INSTALL_PREFIX}")

# Set the install configuration name.
if(NOT DEFINED CMAKE_INSTALL_CONFIG_NAME)
  if(BUILD_TYPE)
    string(REGEX REPLACE "^[^A-Za-z0-9_]+" ""
           CMAKE_INSTALL_CONFIG_NAME "${BUILD_TYPE}")
  else()
    set(CMAKE_INSTALL_CONFIG_NAME "Release")
  endif()
  message(STATUS "Install configuration: \"${CMAKE_INSTALL_CONFIG_NAME}\"")
endif()

# Set the component getting installed.
if(NOT CMAKE_INSTALL_COMPONENT)
  if(COMPONENT)
    message(STATUS "Install component: \"${COMPONENT}\"")
    set(CMAKE_INSTALL_COMPONENT "${COMPONENT}")
  else()
    set(CMAKE_INSTALL_COMPONENT)
  endif()
endif()

# Install shared libraries without execute permission?
if(NOT DEFINED CMAKE_INSTALL_SO_NO_EXE)
  set(CMAKE_INSTALL_SO_NO_EXE "0")
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/metagenomix" TYPE SHARED_LIBRARY FILES "/Users/filipfloreani/Development/Android/Metagenomix/app/build/intermediates/cmake/release/obj/armeabi/liblibdivsufsort.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/liblibdivsufsort.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/liblibdivsufsort.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Users/filipfloreani/Library/Android/sdk/ndk-bundle/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/liblibdivsufsort.so")
    endif()
  endif()
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/metagenomix" TYPE FILE FILES
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/libdivsufsort/config.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/libdivsufsort/divsufsort.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/libdivsufsort/divsufsort64.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/libdivsufsort/divsufsort_private.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/libdivsufsort/lfs.h"
    )
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/metagenomix" TYPE SHARED_LIBRARY FILES "/Users/filipfloreani/Development/Android/Metagenomix/app/build/intermediates/cmake/release/obj/armeabi/libedlib.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libedlib.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libedlib.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Users/filipfloreani/Library/Android/sdk/ndk-bundle/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libedlib.so")
    endif()
  endif()
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/metagenomix" TYPE FILE FILES
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/edlib/edlib.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/edlib/edlibcigar.h"
    )
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/metagenomix" TYPE SHARED_LIBRARY FILES "/Users/filipfloreani/Development/Android/Metagenomix/app/build/intermediates/cmake/release/obj/armeabi/libopal.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libopal.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libopal.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Users/filipfloreani/Library/Android/sdk/ndk-bundle/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libopal.so")
    endif()
  endif()
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/metagenomix" TYPE FILE FILES "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/opal/opal.h")
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/metagenomix" TYPE FILE FILES
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/align.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/align_extend.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/align_profile.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/align_split.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/alignment_free.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/arg_parse.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/bam_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/basic.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/bed_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/consensus.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/file.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/find.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/gff_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/graph_algorithms.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/graph_align.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/graph_msa.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/graph_types.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/index.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/journaled_set.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/map.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/math.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/modifier.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/parallel.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/parse_lm.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/pipe.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/platform.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/random.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/realign.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/reduced_aminoacid.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/roi_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/score.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/seeds.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/seq_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/sequence.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/sequence_journaled.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/simple_intervals_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/statistics.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/store.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/stream.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/system.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/translation.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/ucsc_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/vcf_io.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/libs/seqan/version.h"
    )
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/metagenomix" TYPE SHARED_LIBRARY FILES "/Users/filipfloreani/Development/Android/Metagenomix/app/build/intermediates/cmake/release/obj/armeabi/libgraphmap.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libgraphmap.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libgraphmap.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Users/filipfloreani/Library/Android/sdk/ndk-bundle/toolchains/arm-linux-androideabi-4.9/prebuilt/darwin-x86_64/bin/arm-linux-androideabi-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libgraphmap.so")
    endif()
  endif()
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/metagenomix" TYPE FILE FILES
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/algorithm/fenwick.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/alignment/alignment.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/alignment/alignment_wrappers.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/alignment/cigargen.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/argparser/argparser.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/mapping_data.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/path_graph_entry.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/range.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/raw_alignment.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/region.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/results.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/score_registry.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/containers/vertices.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/graphmap/filter_anchors.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/graphmap/graphmap.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/index/index.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/index/index_hash.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/index/index_owler.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/index/index_sa.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/index/index_spaced_hash.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/index/index_spaced_hash_fast.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/log_system/log_system.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/owler/dpfilter.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/owler/owler.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/owler/owler_data.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/program_parameters.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sequences/kseq.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sequences/sequence_alignment.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sequences/sequence_file.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sequences/sequence_gfa.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sequences/sequence_test.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sequences/single_sequence.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/internal/densehashtable.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/internal/hashtable-common.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/internal/libc_allocator_with_realloc.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/internal/sparseconfig.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/internal/sparsehashtable.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/template_util.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/sparsehash/type_traits.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/utility/evalue.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/utility/evalue_constants.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/utility/utility_conversion-inl.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/graphmap/utility/utility_general.h"
    )
endif()

if(CMAKE_INSTALL_COMPONENT)
  set(CMAKE_INSTALL_MANIFEST "install_manifest_${CMAKE_INSTALL_COMPONENT}.txt")
else()
  set(CMAKE_INSTALL_MANIFEST "install_manifest.txt")
endif()

string(REPLACE ";" "\n" CMAKE_INSTALL_MANIFEST_CONTENT
       "${CMAKE_INSTALL_MANIFEST_FILES}")
file(WRITE "/Users/filipfloreani/Development/Android/Metagenomix/app/.externalNativeBuild/cmake/release/armeabi/${CMAKE_INSTALL_MANIFEST}"
     "${CMAKE_INSTALL_MANIFEST_CONTENT}")
