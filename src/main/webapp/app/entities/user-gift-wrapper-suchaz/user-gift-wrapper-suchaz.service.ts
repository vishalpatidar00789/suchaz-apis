import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UserGiftWrapperSuchaz } from './user-gift-wrapper-suchaz.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserGiftWrapperSuchaz>;

@Injectable()
export class UserGiftWrapperSuchazService {

    private resourceUrl =  SERVER_API_URL + 'api/user-gift-wrappers';

    constructor(private http: HttpClient) { }

    create(userGiftWrapper: UserGiftWrapperSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(userGiftWrapper);
        return this.http.post<UserGiftWrapperSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userGiftWrapper: UserGiftWrapperSuchaz): Observable<EntityResponseType> {
        const copy = this.convert(userGiftWrapper);
        return this.http.put<UserGiftWrapperSuchaz>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserGiftWrapperSuchaz>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserGiftWrapperSuchaz[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserGiftWrapperSuchaz[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserGiftWrapperSuchaz[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserGiftWrapperSuchaz = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserGiftWrapperSuchaz[]>): HttpResponse<UserGiftWrapperSuchaz[]> {
        const jsonResponse: UserGiftWrapperSuchaz[] = res.body;
        const body: UserGiftWrapperSuchaz[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserGiftWrapperSuchaz.
     */
    private convertItemFromServer(userGiftWrapper: UserGiftWrapperSuchaz): UserGiftWrapperSuchaz {
        const copy: UserGiftWrapperSuchaz = Object.assign({}, userGiftWrapper);
        return copy;
    }

    /**
     * Convert a UserGiftWrapperSuchaz to a JSON which can be sent to the server.
     */
    private convert(userGiftWrapper: UserGiftWrapperSuchaz): UserGiftWrapperSuchaz {
        const copy: UserGiftWrapperSuchaz = Object.assign({}, userGiftWrapper);
        return copy;
    }
}
