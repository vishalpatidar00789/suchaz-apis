import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserGiftWrapperSuchaz } from './user-gift-wrapper-suchaz.model';
import { UserGiftWrapperSuchazService } from './user-gift-wrapper-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-user-gift-wrapper-suchaz',
    templateUrl: './user-gift-wrapper-suchaz.component.html'
})
export class UserGiftWrapperSuchazComponent implements OnInit, OnDestroy {
userGiftWrappers: UserGiftWrapperSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userGiftWrapperService: UserGiftWrapperSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.userGiftWrapperService.query().subscribe(
            (res: HttpResponse<UserGiftWrapperSuchaz[]>) => {
                this.userGiftWrappers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUserGiftWrappers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserGiftWrapperSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInUserGiftWrappers() {
        this.eventSubscriber = this.eventManager.subscribe('userGiftWrapperListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
