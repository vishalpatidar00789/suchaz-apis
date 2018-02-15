import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { StoreSuchaz } from './store-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<StoreSuchaz>;

@Injectable()
export class StoreSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/stores';

    constructor(private http: HttpClient) { }

    create(store: StoreSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(store);
        return this.http.post<StoreSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(store: StoreSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(store);
        return this.http.put<StoreSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<StoreSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<StoreSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<StoreSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<StoreSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: StoreSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<StoreSuchaz[]>): HttpResponse<StoreSuchaz[]> {
        const jsonResponse: StoreSuchaz[] = res.body;
        const body: StoreSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to StoreSuchaz.
     */
    private convertItemFromServer(store: StoreSuchaz): StoreSuchaz {
        const copy: StoreSuchaz = Object.assign({}, store);
        return copy;
    }

    /**
     * Convert a StoreSuchaz to a JSON which can be sent to the server.
     */
    private convert(store: StoreSuchaz): StoreSuchaz {
        const copy: StoreSuchaz = Object.assign({}, store);
        return copy;
    }
}
