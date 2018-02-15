import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CategorySuchaz } from './category-suchaz.model';
import { CategorySuchazService } from './category-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-category-suchaz',
    templateUrl: './category-suchaz.component.html'
})
export class CategorySuchazComponent implements OnInit, OnDestroy {
categories: CategorySuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private categoryService: CategorySuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.categoryService.query().subscribe(
            (res: HttpResponse<CategorySuchaz[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCategories();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CategorySuchaz) {
        return item.id;
    }
    registerChangeInCategories() {
        this.eventSubscriber = this.eventManager.subscribe('categoryListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
