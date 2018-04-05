import { delay } from '../utils'
import request from '../utils/request'

export const login = async () => {
  return request({
    url: '/user/login',
    method: 'post',
    data: {
      username: 'admin',
      password: 'jet@biobank',
      rememberMe: true
    }
  })
}

export const logout = async () => {
  return request({
    url: '/user/logout',
    method: 'get',
    data: {
    }
  })
}

export const test = async () => {
  return request({
    url: '/user',
    method: 'get',
    data: {},
  })
}