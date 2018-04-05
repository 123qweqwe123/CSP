
import queryString from 'query-string'
import { request, config } from '../utils'

const { api } = config
const { dataversionlogs, currentVersionLogs } = api
//const { versions, updateVersion, versionLogs } = dict

export async function queryDictVersions(params) {
  return request({
    url: currentVersionLogs,
    method: 'get',
    data: params,
  })
}

export async function queryLogs(params) {
  return request({
    url: dataversionlogs,
    method: 'get',
    data: params,
  })
}

export async function update(params) {
  return request({
    url: dataversionlogs,
    method: 'post',
    data: params,
  })
}

