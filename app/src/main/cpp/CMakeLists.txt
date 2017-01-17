cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

#add_library(graphmap_lib SHARED
#            "graphmap/main.cc"
#            "graphmap/program_parameters.cc"
#            "graphmap/alignment/alignment.cc"
#            "graphmap/alignment/alignment_wrappers.cc"
#            "graphmap/alignment/anchored.cc"
#            "graphmap/alignment/cigargen.cc"
#            "graphmap/alignment/semiglobal.cc"
#            "graphmap/argumentparser/argparser.cc"
#            "graphmap/containers/mapping_data.cc"
#            "graphmap/containers/path_graph_entry.cc"
#            "graphmap/containers/region.cc"
#            "graphmap/containers/score_registry.cc"
#            "graphmap/containers/vertices.cc"
#            "graphmap/graphmap/core_graphmap.cc"
#            "graphmap/graphmap/experimental.cc"
#            "graphmap/graphmap/filter_anchors.cc"
#            "graphmap/graphmap/graphmap.cc"
#            "graphmap/graphmap/lcs_anchored.cc"
#            "graphmap/graphmap/lcs_semiglobal.cc"
#            "graphmap/graphmap/process_read.cc"
#            "graphmap/graphmap/region_selection.cc"
#            "graphmap/index/index.cc"
#            "graphmap/index/index_hash.cc"
#            "graphmap/index/index_owler.cc"
#            "graphmap/index/index_sa.cc"
#            "graphmap/index/index_spaced_hash.cc"
#            "graphmap/index/index_spaced_hash_fast.cc"
#            "graphmap/libs/edlib.cc"
#            "graphmap/libs/edlibcigar.cc"
#            "graphmap/libs/opal.cc"
#            "graphmap/libs/libdivsufsort-2.0.1-64bit/divsufsort.cc"
#            "graphmap/libs/libdivsufsort-2.0.1-64bit/sssort.cc"
#            "graphmap/libs/libdivsufsort-2.0.1-64bit/trsort.cc"
#            "graphmap/libs/libdivsufsort-2.0.1-64bit/utils.cpp"
#            "graphmap/log_system/log_system.cc"
#            "graphmap/owler/dpfilter.cc"
#            "graphmap/owler/owler.cc"
#            "graphmap/owler/owler_data.cc"
#            "graphmap/owler/owler_experimental.cc"
#            "graphmap/owler/process_read.cc"
#            "graphmap/sequences/sequence_alignment.cc"
#            "graphmap/sequences/sequence_alignment_test.cc"
#            "graphmap/sequences/sequence_file.cc"
#            "graphmap/sequences/sequence_gfa.cc"
#            "graphmap/sequences/sequence_gfa_test.cc"
#            "graphmap/sequences/single_sequence.cc"
#            "graphmap/utility/evalue.cc"
#            "graphmap/utility/evalue_constants.cc"
#            "graphmap/utility/utility_general.cc"
#)

set(LINKER_FLAGS "${LINKER_FLAGS} -Wl,--as-needed -Wl,--gc-sections -Wl,--no-undefined -Wl,--strip-all -Wl,-rpath-link=${ANDROID_NDK_SYSROOT}/usr/lib/ -L${ANDROID_NDK_SYSROOT}/usr/lib/")

#include_directories(src/main/cpp/graphmap/algorithm
#                    src/main/cpp/graphmap/alignment
#                    src/main/cpp/graphmap/argumentparser
#                    src/main/cpp/graphmap/containers
#                    src/main/cpp/graphmap/index
#                    src/main/cpp/graphmap/libs
#                    src/main/cpp/graphmap/libs/libduvsufsort-2.0.1-64bit
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/align
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/align_extend
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/align_profile
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/align_split
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/alignment_free
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/arg_parse
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/bam_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/basic
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/bed_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/consensus
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/file
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/find
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/gff_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/graph_algorithms
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/graph_align
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/graph_msa
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/graph_types
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/index
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/journaled_set
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/map
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/math
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/misc
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/modifier
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/parallel
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/parse_im
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/pipe
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/platform
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/random
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/realign
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/reduced_aminoacid
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/roi_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/score
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/seeds
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/seq_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/sequence
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/sequence_journaled
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/simple_intervals_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/statistics
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/store
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/stream
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/system
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/translation
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/ucsc_io
#                    src/main/cpp/graphmap/libs/seqan-2.0.1/seqan/vcf_io
#                    src/main/cpp/graphmap/log_system
#                    src/main/cpp/graphmap/owler
#                    src/main/cpp/graphmap/sequences
#                    src/main/cpp/graphmap/sparsehash
#                    src/main/cpp/graphmap/utility
#)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

#target_link_libraries( # Specifies the target library.
#                       graphmap_lib

                       # Links the target library to the log library
                       # included in the NDK.
#                       ${log-lib} )
