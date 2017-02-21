cmake_minimum_required(VERSION 3.4.1)

project(metagenomix)

set(LINKER_FLAGS "${LINKER_FLAGS} -Wl,--as-needed -Wl,--gc-sections -Wl,--no-undefined -Wl,--strip-all -Wl,-rpath-link=${ANDROID_NDK_SYSROOT}/usr/lib/ -L${ANDROID_NDK_SYSROOT}/usr/lib/")


# LIBDIVSUFSORT LIBRARY
# Find source files
file(GLOB SOURCES_LDS libs/libdivsufsort/*.cpp)

# Include header files
include_directories(libs/libdivsufsort)

# Create shared library
add_library(libdivsufsort SHARED ${SOURCES_LDS})

# Install library
install(TARGETS libdivsufsort DESTINATION lib/${PROJECT_NAME})

# Install library headers
file(GLOB HEADERS_LDS libs/libdivsufsort/*.h)
install(FILES ${HEADERS_LDS} DESTINATION include/${PROJECT_NAME})


# EDLIB LIBRARY
file(GLOB SOURCES_EDL libs/edlib/*.cpp)

include_directories(libs/edlib)

add_library(edlib SHARED ${SOURCES_EDL})

install(TARGETS edlib DESTINATION lib/${PROJECT_NAME})

file(GLOB HEADERS_EDL libs/edlib/*.h)
install(FILES ${HEADERS_EDL} DESTINATION include/${PROJECT_NAME})


# OPAL LIBRARY
file(GLOB SOURCES_OPL libs/opal/*.cpp)

include_directories(libs/opal)

add_library(opal SHARED ${SOURCES_OPL})

install(TARGETS opal DESTINATION lib/${PROJECT_NAME})

file(GLOB HEADERS_OPL libs/opal/*.h)
install(FILES ${HEADERS_OPL} DESTINATION include/${PROJECT_NAME})


# SEQAN LIBRARY
# file(GLOB HEADERS_SEQ libs/seqan/*.h)
# install(FILES ${HEADERS_SEQ} DESTINATION include/${PROJECT_NAME})


# GRAPHMAP LIBRARY
file(GLOB_RECURSE SOURCES_GMP graphmap/*.cc)

include_directories(graphmap)

add_library(graphmap SHARED ${SOURCES_GMP})

install(TARGETS graphmap DESTINATION lib/${PROJECT_NAME})

file(GLOB_RECURSE HEADERS_GMP graphmap/*.h)
install(FILES ${HEADERS_GMP} DESTINATION include/${PROJECT_NAME})
