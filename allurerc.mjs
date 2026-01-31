export default {
  name: "OpenCart Automation Report",

  // ====== HISTORY CONFIG (very important) ======
  historyPath: "./allure-history/allure-history.jsonl",
  appendHistory: true,

  // ====== KNOWN ISSUES CONFIG (optional but powerful) ======
  knownIssuesPath: "./config/known.json",

  // ====== GLOBAL VARIABLES SHOWN ON TOP ======
  variables: {
    "Project": "OpenCart UI Automation",
    "Framework": "Selenium + TestNG",
  },

  // ====== ENVIRONMENTS GROUPING ======
  environments: {
    staging: {
      matcher: ({ labels }) =>
        labels.some(l => l.name === "env" && l.value === "staging"),
      variables: {
        "Environment": "Staging",
        "URL": "https://staging.opencart.com"
      }
    },

    production: {
      matcher: ({ labels }) =>
        labels.some(l => l.name === "env" && l.value === "production"),
      variables: {
        "Environment": "Production",
        "URL": "https://www.opencart.com"
      }
    }
  }
};
