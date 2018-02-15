import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CategorySuchaz } from './category-suchaz.model';
import { CategorySuchazService } from './category-suchaz.service';

@Component({
    selector: 'jhi-category-suchaz-detail',
    templateUrl: './category-suchaz-detail.component.html'
})
export class CategorySuchazDetailComponent implements OnInit, OnDestroy {

    category: CategorySuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private categoryService: CategorySuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCategories();
    }

    load(id) {
        this.categoryService.find(id)
            .subscribe((categoryResponse: HttpResponse<CategorySuchaz>) => {
                this.category = categoryResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCategories() {
        this.eventSubscriber = this.eventManager.subscribe(
            'categoryListModification',
            (response) => this.load(this.category.id)
        );
    }
}
