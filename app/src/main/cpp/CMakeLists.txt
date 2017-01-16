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

add_definitions("-fopenmp")
find_package(OpenMP)
IF(OPENMP_FOUND)
    set (CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${OpenMP_C_FLAGS}")
    set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${OpenMP_CXX_FLAGS}")
ENDIF(OPENMP_FOUND)

#include_directories("/algorithm"
#                    "/alignment"
#                    "/argumentparser"
#                    "/containers"
#                    "/index"
#                    "/libs"
#                    "/libs/seqan"
#                    "/libs/seqan/align"
#                    "/libs/seqan/align_extend"
#                    "/libs/seqan/align_profile"
#                    "/libs/seqan/align_split"
#                    "/libs/seqan/alignment_free"
#                    "/libs/seqan/arg_parse"
#                    "/libs/seqan/bam_io"
#                    "/libs/seqan/basic"
#                    "/libs/seqan/bed_io"
#                    "/libs/seqan/consensus"
#                    "/libs/seqan/file"
#                    "/libs/seqan/find"
#                    "/libs/seqan/gff_io"
#                    "/libs/seqan/graph_algorithms"
#                    "/libs/seqan/graph_align"
#                    "/libs/seqan/graph_msa"
#                    "/libs/seqan/graph_types"
#                    "/libs/seqan/index"
#                    "/libs/seqan/journaled_set"
#                    "/libs/seqan/map"
#                    "/libs/seqan/math"
#                    "/libs/seqan/misc"
#                    "/libs/seqan/modifier"
#                    "/libs/seqan/parallel"
#                    "/libs/seqan/parse_im"
#                    "/libs/seqan/pipe"
#                    "/libs/seqan/platform"
#                    "/libs/seqan/random"
#                    "/libs/seqan/realign"
#                    "/libs/seqan/reduced_aminoacid"
#                    "/libs/seqan/roi_io"
#                    "/libs/seqan/score"
#                    "/libs/seqan/seeds"
#                    "/libs/seqan/seq_io"
#                    "/libs/seqan/sequence"
#                    "/libs/seqan/sequence_journaled"
#                    "/libs/seqan/simple_intervals_io"
#                    "/libs/seqan/statistics"
#                    "/libs/seqan/store"
#                    "/libs/seqan/stream"
#                    "/libs/seqan/system"
#                    "/libs/seqan/translation"
#                    "/libs/seqan/ucsc_io"
#                    "/libs/seqan/vcf_io"
#                    "/log_system"
#                    "/owler"
#                    "/sequences"
#                    "/sparsehash"
#                    "/utility"
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
