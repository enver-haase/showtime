@echo off
timeout 20
mvn --activate-profiles=ci-tests verify
