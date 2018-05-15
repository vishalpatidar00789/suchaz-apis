import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { SuchAzMenuSuchaz } from './such-az-menu-suchaz.model';
import { SuchAzMenuSuchazService } from './such-az-menu-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-such-az-menu-suchaz',
    templateUrl: './such-az-menu-suchaz.component.html'
})
export class SuchAzMenuSuchazComponent implements OnInit, OnDestroy {
suchAzMenus: SuchAzMenuSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private suchAzMenuService: SuchAzMenuSuchazService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.suchAzMenuService.query().subscribe(
            (res: HttpResponse<SuchAzMenuSuchaz[]>) => {
                this.suchAzMenus = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInSuchAzMenus();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: SuchAzMenuSuchaz) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInSuchAzMenus() {
        this.eventSubscriber = this.eventManager.subscribe('suchAzMenuListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
