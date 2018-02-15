import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BuyLaterListItemSuchaz } from './buy-later-list-item-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BuyLaterListItemSuchaz>;

@Injectable()
export class BuyLaterListItemSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/buy-later-list-items';

    constructor(private http: HttpClient) { }

    create(buyLaterListItem: BuyLaterListItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(buyLaterListItem);
        return this.http.post<BuyLaterListItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(buyLaterListItem: BuyLaterListItemSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(buyLaterListItem);
        return this.http.put<BuyLaterListItemSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BuyLaterListItemSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BuyLaterListItemSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<BuyLaterListItemSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BuyLaterListItemSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BuyLaterListItemSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BuyLaterListItemSuchaz[]>): HttpResponse<BuyLaterListItemSuchaz[]> {
        const jsonResponse: BuyLaterListItemSuchaz[] = res.body;
        const body: BuyLaterListItemSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BuyLaterListItemSuchaz.
     */
    private convertItemFromServer(buyLaterListItem: BuyLaterListItemSuchaz): BuyLaterListItemSuchaz {
        const copy: BuyLaterListItemSuchaz = Object.assign({}, buyLaterListItem);
        return copy;
    }

    /**
     * Convert a BuyLaterListItemSuchaz to a JSON which can be sent to the server.
     */
    private convert(buyLaterListItem: BuyLaterListItemSuchaz): BuyLaterListItemSuchaz {
        const copy: BuyLaterListItemSuchaz = Object.assign({}, buyLaterListItem);
        return copy;
    }
}
