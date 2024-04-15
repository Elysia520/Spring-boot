<template>
  <div id="app">
    <div id="chatLog">
      <div v-for="(message, index) in messages" :key="index" :class="message.author">
        {{ message.text }}
      </div>
    </div>
    <textarea v-model="userMessage" placeholder="Type your message here..."></textarea>
    <button @click="sendMessage">Send</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      userMessage: '',
      messages: []
    };
  },
  methods: {
    sendMessage() {
      const userText = this.userMessage.trim();
      if (userText) {
        this.messages.push({ text: userText, author: 'userMessage' });
        axios.post('/chat', { message: userText })
            .then(response => {
              this.messages.push({ text: response.data, author: 'gptResponse' });
            })
            .catch(error => console.error('There was an error:', error));
      }
      this.userMessage = ''; // Clear input after sending
    }
  }
};
</script>

<style>
#chatLog {
  border: 1px solid #ccc;
  padding: 10px;
  height: 300px;
  overflow-y: scroll;
}

.userMessage {
  color: blue;
}

.gptResponse {
  color: green;
}

textarea {
  width: 100%;
  margin-top: 10px;
}
</style>
