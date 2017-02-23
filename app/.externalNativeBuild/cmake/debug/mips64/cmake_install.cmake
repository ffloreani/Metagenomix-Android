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
    set(CMAKE_INSTALL_CONFIG_NAME "Debug")
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
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/lib/metagenomix" TYPE SHARED_LIBRARY FILES "/Users/filipfloreani/Development/Android/Metagenomix/app/build/intermediates/cmake/debug/obj/mips64/libminimap.so")
  if(EXISTS "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libminimap.so" AND
     NOT IS_SYMLINK "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libminimap.so")
    if(CMAKE_INSTALL_DO_STRIP)
      execute_process(COMMAND "/Users/filipfloreani/Library/Android/sdk/ndk-bundle/toolchains/mips64el-linux-android-4.9/prebuilt/darwin-x86_64/bin/mips64el-linux-android-strip" "$ENV{DESTDIR}${CMAKE_INSTALL_PREFIX}/lib/metagenomix/libminimap.so")
    endif()
  endif()
endif()

if("${CMAKE_INSTALL_COMPONENT}" STREQUAL "Unspecified" OR NOT CMAKE_INSTALL_COMPONENT)
  file(INSTALL DESTINATION "${CMAKE_INSTALL_PREFIX}/include/metagenomix" TYPE FILE FILES
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/bseq.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/kdq.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/khash.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/kseq.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/ksort.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/kvec.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/minimap.h"
    "/Users/filipfloreani/Development/Android/Metagenomix/app/src/main/cpp/minimap/sdust.h"
    )
endif()

if(CMAKE_INSTALL_COMPONENT)
  set(CMAKE_INSTALL_MANIFEST "install_manifest_${CMAKE_INSTALL_COMPONENT}.txt")
else()
  set(CMAKE_INSTALL_MANIFEST "install_manifest.txt")
endif()

string(REPLACE ";" "\n" CMAKE_INSTALL_MANIFEST_CONTENT
       "${CMAKE_INSTALL_MANIFEST_FILES}")
file(WRITE "/Users/filipfloreani/Development/Android/Metagenomix/app/.externalNativeBuild/cmake/debug/mips64/${CMAKE_INSTALL_MANIFEST}"
     "${CMAKE_INSTALL_MANIFEST_CONTENT}")
