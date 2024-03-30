KBazaar - Online Shopping Application (Workshop)

## Pre-requisites
- Java 17
- [Gradle](https://gradle.org/install/)
- [pre-commit](https://pre-commit.com/#installation)
- Docker

## Getting Started
1. Clone this repository
2. cd to `kbazaar` directory and run `make setup` (if not working you can run pre-commit manually)
3. Run `make test` to run unit tests
4. Run `make run` to start the application

## API Documentation
- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Example for .env

```markdown
export SONAR_HOST_URL={REPLACE_ME}
export SONAR_TOKEN=${SONAR_TOKEN}

# spring config
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=postgres
export SECURITY_JWT_SECRET={REPLACE_ME}

export ENABLED_FEATURE_PROMOTION_LIST_API=false
```


## Existing Features
- List all Shopper GET /shoppers
- Get Shopper by username GET /shoppers/{username}
- List all Products GET /products
- Get Product by sku GET /products/{sku}
- List all Promotions GET /promotions
- Get Promotion by code GET /promotions/{code}


## Infrastructure

### STEP0: Initial Repository

1. ใช้ repository ที่เตรียมไว้ให้ตามกลุ่ม
2. Replace all `<group-id>` ด้วย กลุ่มตัวเอง e.g `group-1`
1. ทำการเพิ่มสมาชิกใน Github ของทีมเพื่อให้สามารถเข้าถึง Repository ได้
1. ไปที่ Settings > Collaborators and teams > Manage access

- กด `Add People`
- เลือก Role เป็น `Admin` ทุกคนเลย


### STEP1: 🎃 Setup AWS Credential

เพื่อให้ Access AWS ได้เราจะกำหนด Credential เข้าไปให้ Shell ของเราก่อน หรือ set ใน .bashrc, .zshrc ก็ได้

```bash
export AWS_ACCESS_KEY_ID=<KEY>
export AWS_SECRET_ACCESS_KEY=<SECRET>
```

### STEP2: 🧾 Terraform

1.ติดตั้ง Terraform ให้เรียบร้อย

1. `cd` ไปที่ `infra/terraform` จากนั้นรัน

2. สั่งเริ่มต้น Terraform

```console
terraform init
```

3. สั่งสร้าง Terraform สร้าง Resource ใน AWS

```console
terraform apply -var group_name="group-<ID>" --auto-approve
```

4. ตอบ `yes` กด enter แล้วรอไปกดกินข้าวก่อน

```console
Do you want to perform these actions?
  Terraform will perform the actions described above.
  Only 'yes' will be accepted to approve.

  Enter a value: yes
```

## STEP3: 🚀 Deploy ยังไง?

เรา Deploy ด้วย ArgoCD ที่อยู่บน AWS EKS ผ่าน Terraform เพื่อให้ใช้งานได้ ต้องเตรียมของดังนี้

### STEP3.1: 🍻 Setup CI/CD

ตั้งค่าสิทธิ์ในการเข้าถึงของ Github Action

1. ไปที่ Settings > Actions > Workflow permissions > Read and write permissions
1. Settings > Secrets and variables > Actions > New Repository
    - SONAR_HOST_URL
    - SONAR_TOKEN

### STEP3.2: 🗂️ แก้ไข DATABASE_URL ให้เป็น url ของทีม

1. find and replace `<db_dev_url>`, `<db_dev_username>`, `<db_dev_password>` ให้เป็น database url DEV connection ของทีม
1. find and replace `<db_prod_url>`, `<db_prod_username>`, `<db_prod_password>` ให้เป็น database url PRODUCTION connection ของทีม

### 🛟 Kubernetes

1.ติดตั้ง AWS CLI ให้เรียบร้อย

2.ติดตั้ง Kubernetes CLI ให้เรียบร้อย

3.เนื่องจากเราใช้ AWS EKS เป็น Kubernetes Cluster ดังนั้นเราต้องเอา Kubernetes Context จาก AWS EKS โดยสั่ง (*มั่นใจว่าเรา run command ที่ terminal เดียวกันกับเรา export AWS_ACCESS_KEY_ID และ AWS_SECRET_ACCESS_KEY*)

```console
aws eks update-kubeconfig --region ap-southeast-1 --name "eks-group-<ID>"
```

4.ลองสั่ง kubectl

```console
kubectl get ns
```

ถ้าได้ผลลัพธ์ประมาณนี้เป็นอันใช้ได้

```console
NAME              STATUS   AGE
default           Active   3d
kube-system       Active   3d
kube-public       Active   3d
kube-node-lease   Active   3d
```

เพิ่ม ingress controller
```console
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.10.0/deploy/static/provider/cloud/deploy.yaml
```

### 💺 ArgoCD

1.รันคำสั่งสร้าง Namespace

```console
kubectl create namespace argocd
```

2.รันคำสั่งติดตั้ง ArgoCD

```console
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
```

3.หารหัสผ่าน ArgoCD ของ `admin` ไว้ก่อน

```console
kubectl -n argocd get secret argocd-initial-admin-secret -o jsonpath="{.data.password}" | base64 -d; echo
```

4.Forword Port เพื่อใช้งาน ArgoCD

```console
kubectl port-forward svc/argocd-server -n argocd 8080:443
```

5. ไปที่ ArgoCD [http://localhost:8080](http://localhost:8080) แล้วใส่ Username `admin` และ Password ที่ได้จากข้อ 3.


### 🚀 Deployment
1. setup gitops สำหรับ development env

- กด `+ New App` แล้วใส่ข้อมูลดังนี้
- Application Name: `group-<ID>-dev`
- Project Name: `default`
- SYNC POLICY: `Automatic`
- ✅ PRUNE RESOURCES
- Repository URL: `https://github.com/<your-github>/workshop`
- Revision: `main`
- Path: `infra/gitops/dev`
- Cluster URL: `https://kubernetes.default.svc`
- กด `Create` มุมบนซ้าย

2. setup gitops สำหรับ production env

- กด `+ New App` แล้วใส่ข้อมูลดังนี้
- Application Name: `group-<ID>-prod`
- Project Name: `default`
- SYNC POLICY: `Automatic`
- ✅ PRUNE RESOURCES
- Repository URL: `https://github.com/<your-account>/workshop`
- Revision: `main`
- Path: `infra/gitops/prod`
- Cluster URL: `https://kubernetes.default.svc`
- กด `Create` มุมบนซ้าย

### 💣 ใช้ AWS เสร็จแล้วอย่าลืม Destroy ทิ้งน๊า **(ห้ามทำในขณะที่กำลังเรียน Workshop อยู่)**

1. ก่อนจะ Destroy ด้วย Terraform ควรจะลบ Applicaton ใน Argo ทิ้งก่อน
1. สั่งรัน Terraform Destroy

```console
terraform destroy -var group_name="group-<ID>" --auto-approve
```
nong todo remove
