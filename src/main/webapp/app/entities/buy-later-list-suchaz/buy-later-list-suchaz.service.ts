import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BuyLaterListSuchaz } from './buy-later-list-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BuyLaterListSuchaz>;

@Injectable()
export class BuyLaterListSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/buy-later-lists';

    constructor(private http: HttpClient) { }

    create(buyLaterList: BuyLaterListSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(buyLaterList);
        return this.http.post<BuyLaterListSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(buyLaterList: BuyLaterListSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(buyLaterList);
        return this.http.put<BuyLaterListSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BuyLaterListSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BuyLaterListSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<BuyLaterListSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BuyLaterListSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BuyLaterListSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BuyLaterListSuchaz[]>): HttpResponse<BuyLaterListSuchaz[]> {
        const jsonResponse: BuyLaterListSuchaz[] = res.body;
        const body: BuyLaterListSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BuyLaterListSuchaz.
     */
    private convertItemFromServer(buyLaterList: BuyLaterListSuchaz): BuyLaterListSuchaz {
        const copy: BuyLaterListSuchaz = Object.assign({}, buyLaterList);
        return copy;
    }

    /**
     * Convert a BuyLaterListSuchaz to a JSON which can be sent to the server.
     */
    private convert(buyLaterList: BuyLaterListSuchaz): BuyLaterListSuchaz {
        const copy: BuyLaterListSuchaz = Object.assign({}, buyLaterList);
        return copy;
    }
}
