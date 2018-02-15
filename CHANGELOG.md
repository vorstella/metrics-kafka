# CHANGELOG

## 2018-02-14
- fixed JavaDoc errors which were breaking build
- changed hardcoded IP addresses in tests to use localhost as a better alternative
- added dependency-check-mavin plugin to scan for vulnerabilities in depdendencies
  - run a check with `mvn dependency-check:check`
  - vulerabilities described at https://nvd/nist.gov/vuln/detail/[vulnerability ID]
  - vulerabilities have been logged to [KNOWN_ISSUES.md](./KNOWN_ISSUES.md)
