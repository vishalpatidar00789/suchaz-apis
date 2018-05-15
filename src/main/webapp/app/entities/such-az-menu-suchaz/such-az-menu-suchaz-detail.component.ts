import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { SuchAzMenuSuchaz } from './such-az-menu-suchaz.model';
import { SuchAzMenuSuchazService } from './such-az-menu-suchaz.service';

@Component({
    selector: 'jhi-such-az-menu-suchaz-detail',
    templateUrl: './such-az-menu-suchaz-detail.component.html'
})
export class SuchAzMenuSuchazDetailComponent implements OnInit, OnDestroy {

    suchAzMenu: SuchAzMenuSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private suchAzMenuService: SuchAzMenuSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSuchAzMenus();
    }

    load(id) {
        this.suchAzMenuService.find(id)
            .subscribe((suchAzMenuResponse: HttpResponse<SuchAzMenuSuchaz>) => {
                this.suchAzMenu = suchAzMenuResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSuchAzMenus() {
        this.eventSubscriber = this.eventManager.subscribe(
            'suchAzMenuListModification',
            (response) => this.load(this.suchAzMenu.id)
        );
    }
}
