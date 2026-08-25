# API Styles

|                  | REST               | gRPC                   | GraphQL               |
|------------------|--------------------|------------------------|-----------------------|
| Transport        | HTTP/1.1           | HTTP/2                 | HTTP (usually POST)   |
| Payload          | JSON (text)        | Protobuf (binary)      | JSON (text)           |
| Contract         | Optional (OpenAPI) | Strict (.proto)        | Strict (schema)       |
| Fetching         | Fixed per endpoint | Fixed per method       | Client-chosen fields  |
| Streaming        | Limited            | First-class            | Subscriptions         |
| Browser-friendly | ✅                 | ⚠️ (needs proxy)       | ✅                    |
| Best for         | Public/CRUD APIs   | Internal microservices | Data-rich frontends   |

---

# AWS Connection Between Two Accounts

## 1. VPC Peering
Use VPC Peering when the VPC CIDR ranges **do not overlap**.

**Steps:**
1. Create a peering connection using `aws ec2 create-vpc-peering-connection` — specify both VPC IDs.
2. Accept the peering request from the target account using `aws ec2 accept-vpc-peering-connection`.
3. Update the route tables in **both** AWS accounts. Add routes for:
   - Destination CIDR block of the peer VPC
   - Target: VPC Peering Connection
4. Verify Security Groups and Network ACLs allow the required traffic.

**Requirement:** VPC CIDR blocks must not overlap.

---

# AI Study

## Semantic cache
- **Keys** — vector representation of the query.
- **Value** — based on how close the vectors are. Uses approximate nearest neighbour (ANN).

## Where the model runs
```
Your VS Code / browser          Internet             Anthropic's servers
┌──────────────────┐                                ┌──────────────────┐
│ your prompt +    │  ──── API request ────────▶    │   Claude model   │
│ code context     │                                │   runs here      │
│                  │  ◀─── API response ───────     │   (predicts text)│
└──────────────────┘                                └──────────────────┘
```

## What happens in the Anthropic server
| Step | What | Why |
|------|------|-----|
| 1 | Text → tokens → numbers | The model only does math on numbers |
| 2 | Numbers pass through billions of fixed weights | Produces the next-token prediction |
| 3 | Repeats one token at a time | Longer replies = more loops = more time |
| 4 | Runs on parallel GPU hardware | Each step is millisecond-fast |
| 5 | Tokens streamed to you live | Feels instant even when it isn't |

## AI Flow
```
"Write a poem"
     │
     ▼
┌─────────────┐   ┌──────────────┐   ┌───────────────┐   ┌──────────┐   ┌─────────┐
│ TOKENIZE    │──▶│ EMBED        │──▶│ TRANSFORMER   │──▶│ LOGITS + │──▶│ SAMPLE  │
│ text→IDs    │   │ IDs→vectors  │   │ f_θ           │   │ SOFTMAX  │   │ 1 token │
└─────────────┘   └──────────────┘   └───────────────┘   └──────────┘   └────┬────┘
      ▲                                                                      │
      └────────────────── append token, feed it all back ────────────────────┘
                                (the autoregressive loop)
```

**1. Text to tokens (numbers)** — `What is AI?` → `["what","is","AI","?"]` → `[101, 23, 55, 87]`.
The tokenizer maps words↔numbers using a byte-pair (BPE) algorithm.

**2. Embedding** — tokens are transformed into embedding vectors.
```
      ┌──────────────────┐                     ┌──────────────────────────┐
x1:   │ ......e1........ │   ->  Transformer   │ u1 = f(x0)               │
x2:   │ ......e2........ │                     │ u2 = f(x0, x1)           │
        .                                          .
        .                                          .
xT:   │ ......eT........ │                     │ uT = f(x0, x1,...,xT-1)  │

"Write a poem" → [8144, 264, 33894] →
row 8144   → [ 0.1, -0.3, ...]   ← embedding vector for "Write"
row 264    → [ 0.9,  0.2, ...]   ← embedding vector for " a"
row 33894  → [-0.4,  0.6, ...]   ← embedding vector for " poem"
```

**3. Transformer** processes the whole embedding output:
`u_t = f_θ(x_1, ..., x_t)  ∈ ℝ^|V|`

**4. Softmax → probability distribution.** Logits are raw/unbounded; softmax turns them into a proper distribution over the whole vocabulary:
```
P(x_1)                = softmax(f(x_0))
P(x_2 | x_1)          = softmax(f(x_0, x_1)) = softmax(u2)
P(x_t | x_1,...,x_t-1)= softmax(u_t)

" fire" → 0.24
" love" → 0.18
" the"  → 0.09
...      (100k+ tokens, all summing to 1.0)

e.g. [1, 2, 3] → D = exp(1)+exp(2)+exp(3) → [exp(1)/D, exp(2)/D, exp(3)/D]
```
Details: `V` vocab, `h` = transformer output last row (`h_t`), `W` = weight matrix.
`logits = h(d) · W(d × |V|)` → dimension `|V|`. Softmax on logits, then take first or top-k.

**5. Pick next token** — after softmax, every vocab token has a probability:
```
" fire" → 0.24   ← temperature or top-k sampling
" love" → 0.18
" ice"  → 0.12
" the"  → 0.09
" water"→ 0.05
...      (thousands more, summing to 1.0)
```

**6. Autoregressive generation** — predict one token, append it, feed the whole sequence back to predict the next; loop until a stop token or the length limit.

---

# AI Agents

`Observe → Thinking → Tools`

**Basic components:** 1) LLM  2) Loop  3) Tools (file read, web search)  4) Memory (CLAUDE.md)

**References:** https://www.youtube.com/watch?v=EsTrWCV0Ph4

---

# Kubernetes

## EKS overall architecture
```
                                    INTERNET / BP CORPORATE NETWORK
                                              │
                        ┌─────────────────────┴──────────────────────┐
                        │  Route 53 (DNS)                             │
                        └─────────────────────┬──────────────────────┘
                                              │
                        ┌─────────────────────▼──────────────────────┐
                        │  Elastic Load Balancer (ALB/NLB)            │  ← lives in public subnets
                        │  + Security Group (inbound :443)            │     (AWS-managed firewall)
                        └─────────────────────┬──────────────────────┘
                                              │
╔══════════════════════════════════════════════▼═══════════════════════════════════════════════╗
║  AWS VPC  (private network: subnets, route tables, NAT gateway for egress)                     ║
║                                                                                                ║
║   ┌───────────────────────────────┐        ┌────────────────────────────────────────────────┐║
║   │  EKS CONTROL PLANE            │        │  DATA PLANE  (your EC2 worker nodes)            │║
║   │  (AWS-managed, hidden)        │        │                                                 │║
║   │                               │        │  ┌────────── Node = EC2 instance ────────────┐  │║
║   │  • API server  ◀──────────────┼────────┼──│  kubelet │ Istio ingress-gw pod           │  │║
║   │  • Scheduler                  │  mgmt  │  │                                            │  │║
║   │  • etcd (cluster state)       │        │  │  ┌─ Pod ─┐ ┌─ Pod ─┐  ← ceres services     │  │║
║   │  • controller-manager         │        │  │  │ app + │ │ app + │                       │  │║
║   └───────────────────────────────┘        │  │  │ Alloy │ │ Alloy │  (sidecar)            │  │║
║                                            │  │  └───────┘ └───────┘                       │  │║
║   ArgoCD (GitOps) ── syncs from ──▶ desired│  └────────────────────────────────────────────┘  │║
║   yalla repo                        state  │  ┌────────── Node = EC2 instance ────────────┐  │║
║                                            │  │  more pods... (KEDA scales replicas)       │  │║
║                                            │  └────────────────────────────────────────────┘  │║
║                                            └─────────────────────────────────────────────────┘║
║                                                              │                                 ║
║   AWS resources the pods use (via IRSA IAM roles):           │                                 ║
║   ┌─────────┐  ┌──────────────┐  ┌────────────┐  ┌──────────▼──────┐                           ║
║   │   S3    │  │ DocumentDB   │  │ Amazon MQ  │  │  Cluster        │  adds/removes EC2 nodes   ║
║   │ bucket  │  │  cluster     │  │  broker    │  │  Autoscaler /   │  when pods need capacity  ║
║   └─────────┘  └──────────────┘  └────────────┘  │  Karpenter      │                           ║
║                                                   └─────────────────┘                          ║
╚════════════════════════════════════════════════════════════════════════════════════════════════╝
```

## B. Traffic flow (a user request → your pod)
```
 1. User's browser         GET https://ceres.bpaws.com/documents
        │
 2. Route 53               resolves hostname → Load Balancer IP
        │
 3. Load Balancer          terminates TLS, forwards to cluster
    + Security Group        [ GATE: is :443 allowed in? ]                 ← L3/L4 network firewall
        │
 4. Istio Ingress Gateway  [ ROUTER: which service for this host+path? ]  ← L7 HTTP routing (your `endpoints:`)
    (ingress pod)
        │
 5. AuthN / AuthZ          [ GUARD: valid JWT? allowed to access? ]       ← requestAuthentication/Authorization (OPA)
        │
 6. Kubernetes Service     [ LOAD-BALANCE: pick a healthy pod ]           ← stable virtual IP over N pods
        │
 7. Pod (ceres app)        handles the request
        │
 8. Downstream calls       ──▶ DocumentDB (read/write)
                           ──▶ S3 (documents)          (auth via IRSA IAM role, no static keys)
                           ──▶ Amazon MQ (messages)
        │
 9. Response               travels back up the same chain to the browser
```
Permission layers a request passes, in order: **Security Group (network) → Ingress (HTTP route) → AuthN/AuthZ (identity) → Service (load-balance) → Pod.**

## C. Metrics & logs collection (observability)
OTel/Alloy path (as seen in ceres' `metrics.py`).
```
        ┌─────────────────────── Worker Node (EC2) ─────────────────────────┐
        │                                                                   │
        │   ┌──────── Pod ─────────┐                                        │
        │   │  ceres app           │                                        │
 METRICS│   │   │ OTLP metrics     │                                        │
        │   │   ▼                  │                                        │
        │   │  Alloy sidecar ──────┼──┐                                     │
        │   └──────────────────────┘  │                                     │
        │                             │                                     │
  LOGS  │   app writes stdout ───▶ node log files ───▶ Alloy/Fluent agent ──┼──┐
        │                             │  (DaemonSet, one per node)          │  │
        │                             │                                     │  │
 TRACES │   app OTLP spans ───▶ Alloy sidecar ────────────────────────────┼─┤  │
        └─────────────────────────────┼────────────────────────────────────┘  │
                                      │                                        │
                                      ▼                                        ▼
        ┌───────────────────────────────────────────────────────────────────────────┐
        │  Observability backends                                                    │
        │   • Metrics  → Prometheus / Mimir     ─┐                                    │
        │   • Logs     → Loki                    ├──▶  GRAFANA  (dashboards + alerts) │
        │   • Traces   → Tempo                  ─┘                                    │
        └───────────────────────────────────────────────────────────────────────────┘
```

---

# Queue Processing

## Quick config cheat-sheet
| Delivery      | Producer acks | Producer retries | Consumer commit           | Extra                                                                     |
|---------------|---------------|------------------|---------------------------|---------------------------------------------------------------------------|
| At-most-once  | 0             | 0                | before processing (auto)  | —                                                                         |
| At-least-once | all           | high             | after processing (manual) | idempotent consumer                                                       |
| Exactly-once  | all           | high             | inside transaction        | `enable.idempotence=true`, `transactional.id`, `isolation.level=read_committed` |

---

# HTTP + gRPC/REST

**HTTP/1.1** — one request at a time per connection. The response must come back before the next request goes out on that connection.
```
HTTP/1.1 (one connection):
  Req A ──────▶
              ◀────── Resp A        (B waits the whole time)
  Req B ──────▶
              ◀────── Resp B
```
Browsers worked around this by opening ~6 parallel connections per host — expensive (6× TCP + TLS handshakes) and still limited. If one response is slow, everything queued behind it stalls → **head-of-line (HOL) blocking**.

**HTTP/2** — multiplexing: many requests/responses share one connection, interleaved as independent "streams." Responses can come back in any order.
```
HTTP/2 (one connection, interleaved):
  Req A ─▶ Req B ─▶ Req C ─▶
        ◀─ Resp B  ◀─ Resp A  ◀─ Resp C     (no waiting; out-of-order OK)
```

## gRPC — API style vs transport
```
        ┌─────────────────────────────────────────┐
        │  API STYLE  (how you model the call)    │
        │                                         │
        │     REST              gRPC              │  ← these two are comparable
        │  (resources +      (procedures +        │
        │   verbs + JSON)     protobuf)           │
        └───────────────────┬─────────────────────┘
                            │ both ride on ↓
        ┌───────────────────┴───────────────────────┐
        │  TRANSPORT                                 │
        │     HTTP/1.1        HTTP/2                 │  ← HTTP is the layer below
        └────────────────────────────────────────────┘
```
**gRPC-Web** — Browsers can't speak raw gRPC (no access to low-level HTTP/2 frames like trailers / full-duplex streams), so a browser can't be a normal gRPC client. gRPC-Web is a variant that runs over what browsers support; it needs a proxy (Envoy, or an in-process handler).

## Resource hints
| Hint            | Meaning                                                                             |
|-----------------|-------------------------------------------------------------------------------------|
| `preload`       | Fetch now, high priority — needed for this page.                                    |
| `prefetch`      | Fetch during idle time — likely needed for the next page/navigation.                |
| `preconnect`    | Just open the connection (DNS + TCP + TLS) early — don't fetch yet. Good for 3rd-party (fonts, analytics). |
| `dns-prefetch`  | Only resolve DNS early — lighter than preconnect.                                   |
| `modulepreload` | Like preload, but for ES modules (handles the dependency graph).                    |

## TCP / SSL / TLS
- **TCP** (Transmission Control Protocol) — how bytes are transferred across servers:
  - Reliable — lost packets are detected and retransmitted.
  - Ordered — bytes arrive in send order (even over different paths).
  - Connection-oriented — both ends establish a connection before data flows.
- **SSL** (Secure Sockets Layer) — deprecated, old way of securing TCP.
- **TLS** (Transport Layer Security) — secures the TCP connection:
  - Encryption — nobody in the middle can read the data (privacy).
  - Integrity — data can't be tampered with undetected.
  - Authentication — you're talking to the real server (via its certificate), not an impostor.

---

# AWS Service Communication Between Accounts

**IAM Role** — roles are defined for compute (EC2, Lambda, etc.). Resources (S3, DocumentDB) have policies that reference roles and provision the permissions needed to use the resource. The compute then assumes the **IRSA** (IAM Role for Service Account) and gets temporary credentials to use the AWS CLI/SDK.
`sts:AssumeRole` is used when compute in one AWS account wants to use another AWS account's role directly.

## Family A — Endpoint/API + IAM services
Cross-account = permissions only, **no network bridge**.

| Service                   | Cross-account via           | Notes                                             |
|---------------------------|-----------------------------|---------------------------------------------------|
| DynamoDB                  | IAM + resource-based policy | Same model as S3 — API + IAM, no VPC needed       |
| SQS (queues)              | IAM + queue policy          | The messaging service that's like S3 (unlike AMQ) |
| SNS (pub/sub)             | IAM + topic policy          | Cross-account publish/subscribe, no networking    |
| KMS (encryption keys)     | IAM + key policy            | Same as the S3-KMS discussion                     |
| Secrets Manager           | IAM + resource policy       | Share secrets across accounts                     |
| Kinesis (streams)         | IAM                         | Managed streaming, endpoint-based                 |
| Lambda (invoke)           | IAM + resource policy       | Cross-account invoke, no VPC                      |
| ECR (container images)    | IAM + repository policy      | Pull images cross-account                         |
| STS / IAM role assumption | Trust policy                | The foundation of most cross-account access       |

**Common trait:** all have a resource-based policy (bucket/queue/topic/key policy) — the "other half" that opts in a foreign account, exactly like an S3 bucket policy.

## Family B — In-VPC services
Cross-account = **network bridge required** (VPC peering / PrivateLink / Transit Gateway + security groups + credentials).

| Service                       | Why it needs a bridge                        |
|-------------------------------|----------------------------------------------|
| Amazon MQ (AMQ)               | Broker inside a VPC                           |
| RDS / Aurora                  | Database instances in subnets                |
| ElastiCache (Redis/Memcached) | In-VPC nodes                                 |
| DocumentDB                    | In-VPC cluster                               |
| OpenSearch (VPC mode)         | In-VPC                                        |
| EC2 / EKS pods / private ALBs | In-VPC compute                               |
| MSK (managed Kafka)           | Brokers in a VPC (even Kafka needs a bridge) |

## Rule of thumb
- **Family A** (S3, DynamoDB, SQS, SNS, KMS, …) → cross-account via **permissions only**, no bridge.
- **Family B** (AMQ, RDS, DocumentDB, ElastiCache, MSK, …) → cross-account needs **network connectivity**.

---

# AWS Services vs Real-World / OSS Equivalents

## Streaming / Messaging
| Real-world / OSS      | AWS                                        | Notes                                          |
|-----------------------|--------------------------------------------|------------------------------------------------|
| Apache Kafka          | Kinesis Data Streams, or MSK (managed Kafka) | Kinesis = AWS's own; MSK = actual Kafka, managed |
| RabbitMQ / ActiveMQ   | SQS (+ Amazon MQ for real RabbitMQ/ActiveMQ) | SQS is queue-only, no Kafka-style replay       |
| Pub/Sub (topics)      | SNS                                        | Fan-out notifications                          |
| Kafka Streams / Flink | Kinesis Data Analytics / Managed Flink     | Stream processing                              |

## Databases
| Real-world / OSS                  | AWS                                          |
|-----------------------------------|----------------------------------------------|
| PostgreSQL/MySQL/Oracle/SQLServer | RDS (managed) or Aurora (cloud-native PG/MySQL) |
| MongoDB                           | DocumentDB                                    |
| Cassandra                         | Keyspaces                                     |
| Redis / Memcached                 | ElastiCache                                   |
| Neo4j (graph)                     | Neptune                                       |
| InfluxDB / time-series            | Timestream                                    |
| Data warehouse (Teradata, Vertica)| Redshift                                      |

## Compute
| Real-world / OSS           | AWS                                          |
|----------------------------|----------------------------------------------|
| Physical/virtual server    | EC2                                          |
| Docker host                | ECS / Fargate (serverless containers)        |
| Kubernetes                 | EKS                                          |
| Cron job / script on a box | Lambda (event-driven) or EventBridge Scheduler |
| On-prem batch scheduler    | AWS Batch                                    |

## Storage
| Real-world / OSS   | AWS                                        |
|--------------------|--------------------------------------------|
| File on disk / NAS | S3 (object), EFS (NFS file), EBS (block/disk) |
| FTP server         | Transfer Family                            |
| Tape backup/archive| S3 Glacier                                 |

## Analytics / Big Data
| Real-world / OSS         | AWS                                        |
|--------------------------|--------------------------------------------|
| Apache Spark / Hadoop    | EMR (managed Spark/Hadoop) or Glue (serverless Spark ETL) |
| Hive / Presto over files | Athena (query S3 with SQL)                 |
| Airflow                  | MWAA (Managed Airflow) or Step Functions   |
| Grafana / Prometheus     | Managed Grafana / Managed Prometheus (AMP) |

## Networking / Infra
| Real-world / OSS      | AWS                              |
|-----------------------|----------------------------------|
| Nginx / HAProxy LB    | ELB / ALB / NLB                  |
| DNS (BIND)            | Route 53                         |
| CDN (Varnish, Akamai) | CloudFront                       |
| Firewall / VLAN       | Security Groups / VPC / NACLs    |
| VPN                   | Site-to-Site VPN / Direct Connect|

## Identity / Security
| Real-world / OSS        | AWS                                      |
|-------------------------|------------------------------------------|
| LDAP / Active Directory | IAM + Directory Service / IAM Identity Center |
| Vault (secrets)         | Secrets Manager / Parameter Store        |
| TLS cert (Let's Encrypt)| ACM (Certificate Manager)                |
