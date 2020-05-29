# OPEN CA
open source PKI System

 ### 该项目遵循RFC中定义的PKI标准 支持国产SM2、SM3、SM4算法 支持BOTAN、COST等三类算法
 ### 该项目为开源项目, LICENSE为Apache V2.0
 
 # 项目设计
  ## 

  ### 1.概述

    1.1 设计思路
         主体使用spring boot框架，前端页面使用vue框架,快速开发。采用前后端分离的方式，restful标准接口交互，耦合性更低，扩展性更强。
    1.2 设计概要
        1.2.1 主体设计
            主体主要提供证书管理和颁发服务，并提供证书检查等辅助功能
        1.2.2 核心功能包设计
            该包主要提供证书生成和密钥处理的功能,可以支持多种密钥算法及添加自定义的算法提供者,支持加入硬件加密机
    1.3 主要模块介绍
        1.3.1 CA模块
            CA模块主要用于签发证书、管理证书模版，提供证书策略
        1.3.2 RA模块
            RA模块主要用于生成证书请求文件、证书下载、证书检查等功能
        1.3.3 OCSP模块
            在线证书状态检查服务器、支持证书状态的实时检查功能
        1.3.4 CRL模块
            本地证书吊销文件，常用于服务器离线证书检查，不推荐使用。只作为备份检查手段
        1.3.5 KMC模块
            密钥管理工具,提供密钥生成、密钥分发、密钥备份、密钥销毁、密钥吊销、密钥检查等功能
    1.4 CA模块设计简述
        1.4.1 ca模块主要是为了签发证书、并存储和管理CA证书，维护证书策略并
    1.5 RA模块设计简述
    1.6 OCSP模块设计简述
    1.7 CRL模块设计简述
    1.8 KMC模块设计简述
   
  
  ### 2.安装

    2.1 安装需求
    2.2 安装方式
    2.3 启动方式
    2.4 停止方式
    2.5 系统配置项
  
  ### 3.CA模块设计
  
  ### 4.RA模块设计
  
  ### 5.OCSP模块设计
  
  ### 6.CRL模块设计
  
  ### 7.KMC模块设计
