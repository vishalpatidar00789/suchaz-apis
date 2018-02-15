/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemSuchazComponent } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.component';
import { ItemSuchazService } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.service';
import { ItemSuchaz } from '../../../../../../main/webapp/app/entities/item-suchaz/item-suchaz.model';

describe('Component Tests', () => {

    describe('ItemSuchaz Management Component', () => {
        let comp: ItemSuchazComponent;
        let fixture: ComponentFixture<ItemSuchazComponent>;
        let service: ItemSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemSuchazComponent],
                providers: [
                    ItemSuchazService
                ]
            })
            .overrideTemplate(ItemSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ItemSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.items[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
