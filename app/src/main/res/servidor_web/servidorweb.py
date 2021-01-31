from http.server import BaseHTTPRequestHandler, HTTPServer
from urllib.parse import parse_qs
import json
import sys
from random import randint

class ClasseGestora(BaseHTTPRequestHandler):
    
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def extreu_param_get(self, queryData, key, default=""):
        return queryData.get(key, [default])[0]

    def do_GET(self):
        self._set_headers()
        path, _, query_string = self.path.partition('?')
        query = parse_qs(query_string)
        consulta = parse_qs(query_string)
        nom = self.extreu_param_get(consulta, "nom")
        print('dades rebudes', nom)
        dades= '{"data":"2020-11-20", "nom":'+nom+',"puntuacio":'+str(randint(0, 9))+'}'
        self.wfile.write(dades.encode('utf-8'))

def run(server_class=HTTPServer, handler_class=ClasseGestora, port=8000):
    server_address = ('', port)
    httpd = server_class(server_address, handler_class)
    print('iniciant httpd...')
    httpd.serve_forever()


if __name__ == "__main__":
    from sys import argv
    if len(argv) == 2:
        run(port=int(argv[1]))
    else:
        run()
