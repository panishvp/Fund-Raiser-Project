import axios from 'axios'

const ApiService = {
  getLogin: async function (userToken, emailId) {
    console.log('Inside getLogin')
    const URL = 'http://localhost:8085/user/login'
    return axios({
      method: 'post',
      url: URL,
      params: {'emailId': emailId},
      headers: {'authorization': userToken}
    })
  },
  getLogout: async function (userToken) {
    const URL = 'http://localhost:8085/user/logout'
    return axios({
      method: 'delete',
      url: URL,
      headers: {'authorization': userToken}
    })
  },
  getIsAdmin: async function (userToken) {
    const URL = 'http://localhost:8085/user/'
    return axios({
      method: 'get',
      url: URL,
      headers: {'authorization': userToken}
    })
  }
}
export default ApiService
