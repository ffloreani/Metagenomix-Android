cmake_minimum_required(VERSION 3.4.1)

project(metagenomix)

set(LINKER_FLAGS "${LINKER_FLAGS} -Wl,--as-needed -Wl,--gc-sections -Wl,--no-undefined -Wl,--strip-all -Wl,-rpath-link=${ANDROID_NDK_SYSROOT}/usr/lib/ -L${ANDROID_NDK_SYSROOT}/usr/lib/")

# MINIMAP LIBRARY
file(GLOB SOURCES_MMP minimap/*.c)
include_directories(minimap)
add_library(minimap SHARED ${SOURCES_MMP})

# LINKING ZLIB LIBRARY TO MINIMAP
find_package(ZLIB)
if (ZLIB_FOUND)
    include_directories(${ZLIB_INCLUDE_DIRS})
    target_link_libraries(minimap ${ZLIB_LIBRARIES})
endif()

install(TARGETS minimap DESTINATION lib/${PROJECT_NAME})

file(GLOB_RECURSE HEADERS_MMP minimap/*.h)
install(FILES ${HEADERS_MMP} DESTINATION include/${PROJECT_NAME})
